package hanium.englishfairytale.report.service;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.report.dto.PostReportQueryDto;
import hanium.englishfairytale.report.entity.PostReport;
import hanium.englishfairytale.report.repository.PostReportRepository;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostReportServiceImpl implements PostReportService{
    private final PostReportRepository postReportRepository;
    private final MemberRepository memberRepository;
    private final TaleRepository taleRepository;


    @Override
    @Transactional
    public void report(PostReportQueryDto postReportQueryDto){ // 더 해야할 것 자기자신 신고 예외 처리, 포스트 되지 않은 게시물 예외처리
        Member member = verifyAndGetMember(postReportQueryDto.getMemberId());
        Tale tale = verifyAndGetTale(postReportQueryDto.getTaleId());
        verifyExistedMember(member, tale);
        createPostReport(member, tale);
    }
    @Override
    @Transactional
    public void createPostReport(Member member, Tale tale) {
        PostReport postReport = PostReport.builder()
                .id(1L)
                .member(member)
                .tale(tale)
                .build();
        postReportRepository.save(postReport);
    }

    @Override
    @Transactional
    public Member verifyAndGetMember(Long member_id) {
        return memberRepository.findMemberById(member_id).orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }
    @Override
    @Transactional
    public Tale verifyAndGetTale(Long tale_id) {
        return taleRepository.findTaleByTaleId(tale_id).orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }

    @Override
    @Transactional
    public void verifyExistedMember(Member member, Tale tale) {
        if ( postReportRepository.findByMemberAndTale(member, tale).isPresent()) {
            throw new BusinessException(ErrorCode.EXISTED_REPORT);
        }
    }
}

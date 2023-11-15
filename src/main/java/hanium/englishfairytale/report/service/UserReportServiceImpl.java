package hanium.englishfairytale.report.service;

import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.report.dto.UserReportQueryDto;
import hanium.englishfairytale.report.entity.UserReport;
import hanium.englishfairytale.report.repository.UserReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserReportServiceImpl implements UserReportService {
    private final UserReportRepository userReportRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void report(UserReportQueryDto userReportQueryDto) {
        Member reporterUser = getMember(userReportQueryDto.getReporterId());
        Member reportedUser = getMember(userReportQueryDto.getReportedId());

        UserReport userReport = UserReport.builder()
                .reported(reportedUser)
                .reporter(reporterUser)
                .build();
        userReportRepository.save(userReport);
    }
    @Override
    @Transactional
    public Member getMember(Long member_id) {
        return memberRepository.findMemberById(member_id).orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }
}

package hanium.englishfairytale.member.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberDetailInfo;
import hanium.englishfairytale.member.application.dto.MemberInfo;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.member.infra.MemberQueryDao;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberQueryDao memberQueryDao;
    private final TaleQueryDao taleQueryDao;

    @Transactional
    public void verifyNickname(String nickname) {
        if (findMemberByNickname(nickname).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    @Transactional
    public MemberInfo findMemberInfo(Long memberId) {
        return new MemberInfo(findMember(memberId), countTales(memberId));
    }

    @Transactional
    public MemberDetailInfo findMemberDetailInfo(Long memberId) {
        return new MemberDetailInfo(findMember(memberId));
    }

    private Optional<Member> findMemberByNickname(String nickname) {
        return memberRepository.findMemberByNickname(nickname);
    }

    private Member findMember(Long memberId) {
        return memberQueryDao.findMemberAndImage(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Long countTales(Long memberId) {
        return taleQueryDao.countTales(memberId);
    }
}

package hanium.englishfairytale.member.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberDetailInfo;
import hanium.englishfairytale.member.application.dto.MemberInfo;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final TaleRepository taleRepository;

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
        return memberRepository.findMemberAndImage(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Long countTales(Long memberId) {
        return taleRepository.countTales(memberId);
    }
}

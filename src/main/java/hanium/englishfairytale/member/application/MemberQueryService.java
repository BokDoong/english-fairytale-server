package hanium.englishfairytale.member.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberInfo;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.member.infra.MemberQueryDao;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberQueryDao memberQueryDao;
    private final TaleQueryDao taleQueryDao;

    @Transactional
    public void verifyNickName(String nickName) {
        verifyNicknameDuplicated(nickName);
    }

    @Transactional
    public MemberInfo findMember(Long memberId) {

        int taleCounts = taleQueryDao.countTales(memberId);
        return null;
    }

    @Transactional
    public MemberInfo findMemberDetails(Long memberId) {
        return null;
    }

    private void verifyNicknameDuplicated(String nickName) {
        if (memberRepository.findByMemberNickname(nickName).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }
}

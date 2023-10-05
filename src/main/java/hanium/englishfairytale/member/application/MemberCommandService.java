package hanium.englishfairytale.member.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberCreateCommand;
import hanium.englishfairytale.member.application.dto.CreateMemberResponse;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;

    @Transactional
    public CreateMemberResponse register(MemberCreateCommand memberCreateCommand) {
        return new CreateMemberResponse(createAndSaveMember(memberCreateCommand));
    }

    private Long createAndSaveMember(MemberCreateCommand memberCreateCommand) {
        verifyExistedMember(memberCreateCommand);
        verifyDuplicatedNickname(memberCreateCommand);
        return memberRepository.save(createMember(memberCreateCommand));
    }

    private void verifyDuplicatedNickname(MemberCreateCommand memberCreateCommand) {
        if (memberRepository.findByNickname(memberCreateCommand.getNickname()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    private void verifyExistedMember(MemberCreateCommand memberCreateCommand) {
        if (memberRepository.findByPhoneNumber(memberCreateCommand.getPhoneNumber()).isPresent()) {
            throw new BusinessException(ErrorCode.EXISTED_MEMBER);
        }
    }

    private Member createMember(MemberCreateCommand memberCreateCommand) {
        return Member.builder()
                .name(memberCreateCommand.getName())
                .phoneNumber(memberCreateCommand.getPhoneNumber())
                .nickname(memberCreateCommand.getNickname())
                .email(memberCreateCommand.getEmail())
                .password(memberCreateCommand.getPassword())
                .build();
    }
}

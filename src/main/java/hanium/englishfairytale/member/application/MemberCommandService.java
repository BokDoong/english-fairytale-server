package hanium.englishfairytale.member.application;

import hanium.englishfairytale.common.files.FileManageService;
import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.application.dto.MemberCreateCommand;
import hanium.englishfairytale.member.application.dto.MemberImageUpdateCommand;
import hanium.englishfairytale.member.application.dto.MemberUpdatePasswordCommand;
import hanium.englishfairytale.member.domain.ImageRepository;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberImage;
import hanium.englishfairytale.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final FileManageService fileManageService;

    @Transactional
    public Long register(MemberCreateCommand memberCreateCommand) {
        verifyExistedMember(memberCreateCommand);
        return createAndSaveMember(memberCreateCommand);
    }

    @Transactional
    public void updateNickname(Long memberId, String nickname) {
        Member member = findMember(memberId);
        member.updateNickname(nickname);
    }

    @Transactional
    public void updatePassword(MemberUpdatePasswordCommand updatePasswordCommand) {
        Member member = findMember(updatePasswordCommand.getMemberId());
        member.updatePassword(updatePasswordCommand.getPassword());
    }

    @Transactional
    public void updateMemberImage(MemberImageUpdateCommand imageUpdateCommand) {
        Member member = findMember(imageUpdateCommand.getMemberId());
        member.updateMemberImage(createAndSaveMemberImage(imageUpdateCommand.getImage()));
    }

    @Transactional
    public void deleteMemberImage(Long memberId) {
        Member member = findMember(memberId);
        Long imageId = findImageId(member);
        deleteImage(member, imageId);
    }

    private void deleteImage(Member member, Long imageId) {
        member.makeImageNull();
        imageRepository.delete(imageId);
    }

    private Long findImageId(Member member) {
        verifyImageIsEmpty(member);
        return member.getImageId();
    }

    private void verifyImageIsEmpty(Member member) {
        member.checkImageEmpty();
    }

    private Member findMember(Long id) {
        return memberRepository.findMemberById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Long createAndSaveMember(MemberCreateCommand memberCreateCommand) {
        Member member = createMember(memberCreateCommand);
        if (!checkImageEmpty(memberCreateCommand)) {
            member.putImage(createAndSaveMemberImage(memberCreateCommand.getImage()));
        }
        return saveMember(member);
    }

    private boolean checkImageEmpty(MemberCreateCommand memberCreateCommand) {
        return memberCreateCommand.getImage().isEmpty();
    }

    private Long saveMember(Member member) {
        return memberRepository.save(member);
    }

    private MemberImage createAndSaveMemberImage(MultipartFile file) {
        return new MemberImage(fileManageService.uploadImage(file));
    }

    private void verifyExistedMember(MemberCreateCommand memberCreateCommand) {
        if (memberRepository.findMemberByPhoneNumber(memberCreateCommand.getPhoneNumber()).isPresent()) {
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

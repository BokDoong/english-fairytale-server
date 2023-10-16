package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.MemberCommandService;
import hanium.englishfairytale.member.application.MemberQueryService;
import hanium.englishfairytale.member.application.dto.*;
import hanium.englishfairytale.member.infra.http.dto.MemberLoginDto;
import hanium.englishfairytale.member.infra.http.dto.MemberRegisterDto;
import hanium.englishfairytale.member.infra.http.dto.MemberUpdatePasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberDtoConverter converter;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@Validated @RequestPart MemberRegisterDto memberRegisterDto,
                                         @RequestPart MultipartFile image) {
        return new ResponseEntity<>(memberCommandService.register(toCreateCommand(memberRegisterDto, image)), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Long> login(@Validated @RequestBody MemberLoginDto memberLoginDto) {
        return new ResponseEntity<>(memberCommandService.login(converter.toCommand(memberLoginDto)), HttpStatus.OK);
    }

    @PostMapping("/check")
    public void checkNicknameDuplicated(@RequestParam String nickname) {
        memberQueryService.verifyNickname(nickname);
    }

    @GetMapping("{memberId}")
    public ResponseEntity<MemberInfo> findMemberInfo(@PathVariable Long memberId) {
        return new ResponseEntity<>(memberQueryService.findMemberInfo(memberId), HttpStatus.OK);
    }

    @GetMapping("{memberId}/detail")
    public ResponseEntity<MemberDetailInfo> findMemberDetailInfo(@PathVariable Long memberId) {
        return new ResponseEntity<>(memberQueryService.findMemberDetailInfo(memberId), HttpStatus.OK);
    }

    @PatchMapping("/nickname")
    public void updateNickname(@RequestParam Long memberId, @RequestParam String nickname) {
        memberCommandService.updateNickname(memberId, nickname);
    }

    @PatchMapping("/password/{memberId}")
    public void updatePassword(@PathVariable Long memberId ,@Validated @RequestBody MemberUpdatePasswordDto updatePasswordDto) {
        memberCommandService.updatePassword(toPasswordUpdateCommand(memberId, updatePasswordDto));
    }

    @PatchMapping("/{memberId}/image")
    public void updateImage(@PathVariable Long memberId, @RequestPart MultipartFile image) {
        memberCommandService.updateMemberImage(toImageUpdateCommand(memberId, image));
    }

    @DeleteMapping("/{memberId}/image")
    public void deleteImage(@PathVariable Long memberId) {
        memberCommandService.deleteMemberImage(memberId);
    }

    private MemberRegisterCommand toCreateCommand(MemberRegisterDto memberRegisterDto, MultipartFile image) {
        return converter.toCommand(memberRegisterDto, image);
    }

    private MemberUpdatePasswordCommand toPasswordUpdateCommand(Long memberId, MemberUpdatePasswordDto updatePasswordDto) {
        return converter.toCommand(memberId, updatePasswordDto);
    }

    private MemberImageUpdateCommand toImageUpdateCommand(Long memberId, MultipartFile image) {
        return converter.toCommand(memberId, image);
    }
}

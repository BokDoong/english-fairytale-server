package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.MemberCommandService;
import hanium.englishfairytale.member.application.MemberQueryService;
import hanium.englishfairytale.member.application.dto.*;
import hanium.englishfairytale.member.infra.http.dto.MemberLoginDto;
import hanium.englishfairytale.member.infra.http.dto.MemberRegisterDto;
import hanium.englishfairytale.member.infra.http.dto.MemberUpdatePasswordDto;
import hanium.englishfairytale.post.application.PostQueryService;
import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberDtoConverter converter;
    private final PostQueryService postQueryService;

    @PostMapping("/register")
    public Long register(@Validated @RequestPart MemberRegisterDto memberRegisterDto,
                                         @RequestPart MultipartFile image) {
        return memberCommandService.register(toCreateCommand(memberRegisterDto, image));
    }

    @GetMapping("/login")
    public Long login(@Validated @RequestBody MemberLoginDto memberLoginDto) {
        return memberCommandService.login(converter.toCommand(memberLoginDto));
    }

    @PostMapping("/check")
    public void checkNicknameDuplicated(@RequestParam String nickname) {
        memberQueryService.verifyNickname(nickname);
    }

    @GetMapping("{memberId}")
    public MemberInfo findMemberInfo(@PathVariable Long memberId) {
        return memberQueryService.findMemberInfo(memberId);
    }

    @GetMapping("{memberId}/detail")
    public MemberDetailInfo findMemberDetailInfo(@PathVariable Long memberId) {
        return memberQueryService.findMemberDetailInfo(memberId);
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

    @GetMapping("/{memberId}/posts")
    public List<PostedTalesInfo> findPosts(@PathVariable Long memberId, @RequestParam int offset) {
        return postQueryService.findPostsForMyPage(offset, memberId);
    }

    @GetMapping("/{memberId}/posts/like")
    public List<PostedTalesInfo> findLikedPosts(@PathVariable Long memberId, @RequestParam int offset) {
        return postQueryService.findLikedPostsForMyPage(offset, memberId);
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

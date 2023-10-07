package hanium.englishfairytale.member.infra.http;

import com.amazonaws.Response;
import hanium.englishfairytale.member.application.MemberCommandService;
import hanium.englishfairytale.member.application.MemberQueryService;
import hanium.englishfairytale.member.application.dto.MemberCreateCommand;
import hanium.englishfairytale.member.application.dto.MemberDetailInfo;
import hanium.englishfairytale.member.application.dto.MemberInfo;
import hanium.englishfairytale.member.infra.http.dto.MemberCreateDto;
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
    public ResponseEntity<Long> register(@Validated @RequestPart MemberCreateDto memberCreateDto,
                                         @RequestPart MultipartFile image) {
        return new ResponseEntity<>(memberCommandService.register(toCreateCommand(memberCreateDto, image)), HttpStatus.OK);
    }

    @PostMapping("/check")
    public void checkNicknameDuplicated(@RequestParam String nickName) {
        memberQueryService.verifyNickName(nickName);
    }

    @GetMapping("{memberId}")
    public ResponseEntity<MemberInfo> findMemberInfo(@PathVariable Long memberId) {
        return new ResponseEntity<>(memberQueryService.findMemberInfo(memberId), HttpStatus.OK);
    }

    @GetMapping("{memberId}/detail")
    public ResponseEntity<MemberDetailInfo> findMemberDetailInfo(@PathVariable Long memberId) {
        return new ResponseEntity<>(memberQueryService.findMemberDetailInfo(memberId), HttpStatus.OK);
    }

    private MemberCreateCommand toCreateCommand(MemberCreateDto memberCreateDto, MultipartFile image) {
        return converter.toCommand(memberCreateDto, image);
    }
}

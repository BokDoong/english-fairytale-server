package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.MemberCommandService;
import hanium.englishfairytale.member.application.dto.request.MemberCreateCommand;
import hanium.englishfairytale.member.application.dto.response.CreateMemberResponse;
import hanium.englishfairytale.member.infra.http.dto.MemberCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberDtoConverter converter;

    @PostMapping("/register")
    public ResponseEntity<CreateMemberResponse> register(@Validated @RequestBody MemberCreateDto memberCreateDto) {
        return new ResponseEntity<>(memberCommandService.register(toCreateCommand(memberCreateDto)), HttpStatus.OK);
    }

    private MemberCreateCommand toCreateCommand(MemberCreateDto memberCreateDto) {
        return converter.toCommand(memberCreateDto);
    }
}

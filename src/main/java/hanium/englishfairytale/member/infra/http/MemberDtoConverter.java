package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.dto.MemberRegisterCommand;
import hanium.englishfairytale.member.application.dto.MemberImageUpdateCommand;
import hanium.englishfairytale.member.application.dto.MemberUpdatePasswordCommand;
import hanium.englishfairytale.member.application.dto.MemberLoginCommand;
import hanium.englishfairytale.member.infra.http.dto.MemberLoginDto;
import hanium.englishfairytale.member.infra.http.dto.MemberRegisterDto;
import hanium.englishfairytale.member.infra.http.dto.MemberUpdatePasswordDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MemberDtoConverter {
    public MemberRegisterCommand toCommand(MemberRegisterDto dto, MultipartFile image) {
        return MemberRegisterCommand.builder()
                .phoneNumber(dto.getPhoneNumber())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .image(image)
                .build();
    }

    public MemberUpdatePasswordCommand toCommand(Long memberId, MemberUpdatePasswordDto dto) {
        return MemberUpdatePasswordCommand.builder()
                .memberId(memberId)
                .originalPassword(dto.getOriginalPassword())
                .newPassword(dto.getNewPassword())
                .build();
    }

    public MemberImageUpdateCommand toCommand(Long id, MultipartFile image) {
        return MemberImageUpdateCommand.builder()
                .memberId(id)
                .image(image)
                .build();
    }

    public MemberLoginCommand toCommand(MemberLoginDto dto) {
        return MemberLoginCommand.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}

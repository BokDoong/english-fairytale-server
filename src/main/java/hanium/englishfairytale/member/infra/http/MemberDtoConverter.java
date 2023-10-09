package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.dto.MemberCreateCommand;
import hanium.englishfairytale.member.application.dto.MemberImageUpdateCommand;
import hanium.englishfairytale.member.application.dto.MemberUpdatePasswordCommand;
import hanium.englishfairytale.member.infra.http.dto.MemberCreateDto;
import hanium.englishfairytale.member.infra.http.dto.MemberUpdatePasswordDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MemberDtoConverter {
    public MemberCreateCommand toCommand(MemberCreateDto dto, MultipartFile image) {
        return MemberCreateCommand.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .image(image)
                .build();
    }

    public MemberUpdatePasswordCommand toCommand(MemberUpdatePasswordDto dto) {
        return MemberUpdatePasswordCommand.builder()
                .memberId(dto.getMemberId())
                .password(dto.getPassword())
                .build();
    }

    public MemberImageUpdateCommand toCommand(Long id, MultipartFile image) {
        return MemberImageUpdateCommand.builder()
                .memberId(id)
                .image(image)
                .build();
    }
}

package hanium.englishfairytale.member.infra.http;

import hanium.englishfairytale.member.application.dto.MemberCreateCommand;
import hanium.englishfairytale.member.infra.http.dto.MemberCreateDto;
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
}

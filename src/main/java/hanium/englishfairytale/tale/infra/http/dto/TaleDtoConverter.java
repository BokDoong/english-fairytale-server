package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.tale.application.dto.TaleCreateCommand;
import hanium.englishfairytale.tale.application.dto.TaleUpdateCommand;
import hanium.englishfairytale.tale.domain.ImageStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TaleDtoConverter {
    public TaleCreateCommand toCreateCommand(TaleCreateDto dto, MultipartFile image) {
        return TaleCreateCommand.builder()
                .memberId(dto.getMemberId())
                .model(dto.getModel())
                .keywords(dto.getKeywords())
                .image(image)
                .imageStatus(ImageStatus.of(dto.getImageStatus()))
                .build();
    }

    public TaleUpdateCommand toUpdateCommand(Long taleId, MultipartFile image) {
        return TaleUpdateCommand.builder()
                .taleId(taleId)
                .image(image)
                .build();
    }
}

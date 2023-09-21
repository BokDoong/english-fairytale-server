package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TaleDtoConverter {

    public TaleCreateCommand toCommand(TaleCreateDto dto, MultipartFile image) {
        return TaleCreateCommand.builder()
                .model(dto.getModel())
                .keywords(dto.getKeywords())
                .image(image)
                .build();
    }
}

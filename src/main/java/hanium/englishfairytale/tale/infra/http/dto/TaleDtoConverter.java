package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import org.springframework.stereotype.Component;

@Component
public class TaleDtoConverter {

    public TaleCreateCommand toCommand(TaleCreateDto dto) {
        return TaleCreateCommand.builder()
                .model(dto.getModel())
                .keywords(dto.getKeywords())
                .build();
    }
}

package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import org.springframework.stereotype.Component;

@Component
public class TaleDtoConverter {

    public TaleCreateCommand toCommand(TaleCreateDto dto) {
        TaleCreateDto taleCreateDto = new TaleCreateDto(dto.getModel(), dto.getKeywords());
        return TaleCreateCommand.builder()
                .model(taleCreateDto.getModel())
                .keywords(taleCreateDto.getKeywords())
                .build();
    }
}

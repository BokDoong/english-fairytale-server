package hanium.englishfairytale.tale.domain.factory;

import hanium.englishfairytale.tale.domain.factory.dto.CreateTaleFactoryDto;

import java.util.List;

public interface TaleFactory {
    String getGptResponse(String model, List<CreateTaleFactoryDto> messages);
}

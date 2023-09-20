package hanium.englishfairytale.tale.domain.factory;

import hanium.englishfairytale.tale.domain.factory.dto.CreateTaleDto;

import java.util.List;

public interface TaleFactory {

    String getGptResponse(String model, List<CreateTaleDto> messages);
}

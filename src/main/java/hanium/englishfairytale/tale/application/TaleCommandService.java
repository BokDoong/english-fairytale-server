package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.infra.http.dto.TaleCreateDto;
import hanium.englishfairytale.tale.application.dto.response.TaleCreateResult;

public interface TaleCommandService {
    TaleCreateResult create(TaleCreateDto taleCreateDto);

}

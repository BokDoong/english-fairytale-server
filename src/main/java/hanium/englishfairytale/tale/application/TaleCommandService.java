package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.infra.http.dto.request.TaleCreateDto;
import hanium.englishfairytale.tale.infra.http.dto.response.TaleCreateResponse;

public interface TaleCommandService {
    TaleCreateResponse create(TaleCreateDto taleCreateDto);

}

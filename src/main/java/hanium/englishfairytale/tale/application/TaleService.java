package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.infra.http.dto.response.TaleCreateResponse;

public interface TaleService {
    TaleCreateResponse create(hanium.englishfairytale.tale.infra.http.dto.request.TaleCreateDto taleCreateDto);

}

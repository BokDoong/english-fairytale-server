package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import hanium.englishfairytale.tale.application.dto.response.TaleDetailInfo;

public interface TaleCommandService {
    TaleDetailInfo create(TaleCreateCommand taleCreateCommand);

}

package hanium.englishfairytale.tale.application.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TaleCreateCommand {
    private String model;
    private List<String> keywords;
}

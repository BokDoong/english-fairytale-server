package hanium.englishfairytale.tale.infra.http.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class TaleCreateDto {
    @NotBlank(message = "MODEL_IS_MANDATORY")
    private String model;
    @NotEmpty(message = "KEYWORDS_ARE_MANDATORY")
    private List<String> keywords;

    public TaleCreateDto(String model, List<String> keywords) {
        this.model = model;
        this.keywords = keywords;
    }
}
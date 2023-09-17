package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.exception.BusinessException;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static hanium.englishfairytale.exception.code.ErrorCode.EXCEED_KEYWORD_LIMIT;

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
        validateNumberOfKeywords(keywords);
    }

    private void validateNumberOfKeywords(List<String> keywords) {
        if (keywords.size() > 5) {
            throw new BusinessException(EXCEED_KEYWORD_LIMIT);
        }
    }
}
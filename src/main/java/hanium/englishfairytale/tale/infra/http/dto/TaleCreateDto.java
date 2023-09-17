package hanium.englishfairytale.tale.infra.http.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class TaleCreateDto {
    private String model;
    private List<String> keywords;

    public TaleCreateDto(String model, List<String> keywords) {
        this.model = model;
        this.keywords = keywords;
        validateNumberOfKeywords(keywords);
    }

    private void validateNumberOfKeywords(List<String> keywords) {
        if (keywords.size() > 5) {
//            throw new CustomException()
        }
    }
}
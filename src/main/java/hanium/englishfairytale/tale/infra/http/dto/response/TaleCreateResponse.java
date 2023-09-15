package hanium.englishfairytale.tale.infra.http.dto.response;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.*;

import java.security.Key;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaleCreateResponse {
    private String title;
    private String content;
    private String kor;
    private List<String> keywords;

    public TaleCreateResponse(Tale tale, List<Keyword> keywords) {
        this.title = tale.getTitle();
        this.content = tale.getContent();
        this.kor = tale.getContent();
        for(Keyword keyword:keywords) {
            this.keywords.add(keyword.getWord());
        }
    }
}

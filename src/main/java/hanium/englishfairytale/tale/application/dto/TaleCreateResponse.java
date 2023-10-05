package hanium.englishfairytale.tale.application.dto;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaleCreateResponse {
    private Long taleId;
    private String title;
    private String content;
    private String kor;
    private List<String> keywords;
    private String imgUrl;

    public TaleCreateResponse(Tale tale, List<Keyword> newKeywords) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        this.content = tale.getEngTale();
        this.kor = tale.getKorTale();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();

        keywords = new ArrayList<>();
        for(Keyword keyword:newKeywords) {
            this.keywords.add(keyword.getWord());
        }
    }
}

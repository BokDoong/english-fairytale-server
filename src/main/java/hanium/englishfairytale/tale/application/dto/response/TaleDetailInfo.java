package hanium.englishfairytale.tale.application.dto.response;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaleDetailInfo {
    private String title;
    private String content;
    private String kor;
    private List<String> keywords;

    public TaleDetailInfo(Tale tale, List<Keyword> newKeywords) {
        this.title = tale.getTitle();
        this.content = tale.getContent();
        this.kor = tale.getContent();

        keywords = new ArrayList<>();
        for(Keyword keyword:newKeywords) {
            this.keywords.add(keyword.getWord());
        }
    }
}

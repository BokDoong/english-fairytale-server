package hanium.englishfairytale.tale.application.dto;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TalesInfo {

    private Long taleId;
    private String title;
    private List<String> keywords;

    public TalesInfo(Tale tale, List<Keyword> keywordList) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        keywords = new ArrayList<>();
        for (Keyword keyword : keywordList) {
            keywords.add(keyword.getWord());
        }
    }
}

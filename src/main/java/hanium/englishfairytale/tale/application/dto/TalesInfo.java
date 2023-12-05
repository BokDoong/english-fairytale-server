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
    private String imgUrl;
    private String imgStatus;
    private List<String> keywords;
    private boolean liked;

    public TalesInfo(Long memberId, Tale tale, List<Keyword> keywordList) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();
        this.imgStatus = tale.getImageStatus();
        this.liked = tale.checkMemberLikedPost(memberId);

        keywords = new ArrayList<>();
        for (Keyword keyword : keywordList) {
            keywords.add(keyword.getWord());
        }
    }
}

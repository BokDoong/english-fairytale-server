package hanium.englishfairytale.tale.application.dto;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TaleDetailInfo {

    private Long taleId;
    private String title;
    private String memberName;
    private String engTale;
    private String korTale;
    private String imgUrl;
    private String imageStatus;
    private List<String> keywords;

    public TaleDetailInfo(Tale tale, List<Keyword> keywordList) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        this.memberName = tale.getMemberNickname();
        this.engTale = tale.getEngTale();
        this.korTale = tale.getKorTale();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();
        this.imageStatus = tale.getImageStatus();

        keywords = new ArrayList<>();
        for (Keyword keyword : keywordList) {
            keywords.add(keyword.getWord());
        }
    }
}

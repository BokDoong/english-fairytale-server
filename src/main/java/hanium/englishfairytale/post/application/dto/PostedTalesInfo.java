package hanium.englishfairytale.post.application.dto;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostedTalesInfo {
    private Long taleId;
    private String title;
    private String nickname;
    private String imgUrl;
    private List<String> keywords;
    private int likeCounts;

    public PostedTalesInfo(Tale tale, int likeCounts, List<Keyword> keywords) {
        this.title = tale.getTitle();
        this.nickname = tale.getMemberName();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();
        this.likeCounts = likeCounts;

        this.keywords = new ArrayList<>();
        for (Keyword keyword : keywords) {
            this.keywords.add(keyword.getWord());
        }
    }

    public PostedTalesInfo(Long taleId, String title, String nickname, String imgUrl) {
        this.taleId = taleId;
        this.title = title;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.keywords = new ArrayList<>();
    }

    public void setKeywordContents(List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            this.keywords.add(keyword.getWord());
        }
    }
}

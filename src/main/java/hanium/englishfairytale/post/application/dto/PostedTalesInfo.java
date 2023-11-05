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
    private boolean liked;

    public PostedTalesInfo(Tale tale, int likeCounts, List<Keyword> keywords, boolean liked) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        this.nickname = tale.getMemberNickname();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();
        this.likeCounts = likeCounts;
        this.liked = liked;

        this.keywords = new ArrayList<>();
        for (Keyword keyword : keywords) {
            this.keywords.add(keyword.getWord());
        }
    }

    public PostedTalesInfo(Tale tale, boolean liked) {
        this.taleId = tale.getId();
        this.title = tale.getTitle();
        this.nickname = tale.getMemberNickname();
        this.imgUrl = tale.getImage().getTaleImage() == null ? null : tale.getImage().getUrl();
        this.liked = liked;
        this.keywords = new ArrayList<>();
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

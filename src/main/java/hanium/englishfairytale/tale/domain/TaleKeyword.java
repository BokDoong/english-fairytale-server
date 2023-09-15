package hanium.englishfairytale.tale.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class TaleKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tale_id")
    private Tale tale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="keyword_id")
    private Keyword keyword;

    public static TaleKeyword createTaleKeyword(Tale tale, Keyword keyword) {
        TaleKeyword taleKeyword = new TaleKeyword();
        taleKeyword.setTale(tale);
        taleKeyword.setKeyword(keyword);

        return taleKeyword;
    }

    private void setTale(Tale newTale) {
        tale = newTale;
    }
    private void setKeyword(Keyword newKeyword) {
        keyword = newKeyword;
    }

}

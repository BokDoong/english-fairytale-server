package hanium.englishfairytale.tale.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TaleKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tale_id")
    private Tale tale;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    public TaleKeyword(Tale tale, Keyword keyword) {
        this.tale = tale;
        this.keyword = keyword;
        tale.addTaleKeyword(this);
        keyword.addTaleKeyword(this);
    }

//    public static TaleKeyword createTaleKeyword(Tale tale, Keyword keyword) {
//        TaleKeyword taleKeyword = new TaleKeyword();
//        taleKeyword.setTale(tale);
//        taleKeyword.setKeyword(keyword);
//
//        return taleKeyword;
//    }
//
//    private void setTale(Tale newTale) {
//        this.tale = newTale;
//        newTale.addTaleKeyword(this);
//    }
//
//    private void setKeyword(Keyword newKeyword) {
//        keyword = newKeyword;
//        newKeyword.addTaleKeyword(this);
//    }

}

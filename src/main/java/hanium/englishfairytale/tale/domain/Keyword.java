package hanium.englishfairytale.tale.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String word;

    @OneToMany(mappedBy = "keyword")
    private List<TaleKeyword> taleKeywords = new ArrayList<>();

    @Builder
    public Keyword(String word) {
        this.word = word;
    }

    public void addTaleKeyword(TaleKeyword newTaleKeyword) {
        this.taleKeywords.add(newTaleKeyword);
    }
}

package hanium.englishfairytale.tale.domain;

import com.sun.istack.NotNull;
import hanium.englishfairytale.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static hanium.englishfairytale.exception.code.ErrorCode.DUPLICATED_KEYWORD;
import static hanium.englishfairytale.exception.code.ErrorCode.EXCEED_KEYWORD_LIMIT;

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

    public static void verifyNumberOfKeywords(List<String> keywords) {
        if (keywords.size() > 5) {
            throw new BusinessException(EXCEED_KEYWORD_LIMIT);
        }
    }

    public static void verifyDuplicatedKeywords(List<String> keywords) {
        HashSet<String> duplicatedTestSet = new HashSet<>();
        for (String keyword : keywords) {
            if (!duplicatedTestSet.add(keyword)) {
                throw new BusinessException(DUPLICATED_KEYWORD);
            }
        }
    }
}

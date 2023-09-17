package hanium.englishfairytale.tale.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "content", length = 5000)
    private String content;
    @Column(name = "kor",length = 3000)
    private String kor;
    @Column(name = "created_date")
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "tale")
    private List<TaleKeyword> taleKeywords = new ArrayList<>();

    @Builder
    public Tale(String title, String content,
                String kor) {
        this.title = title;
        this.content = content;
        this.kor = kor;
        this.createdTime = LocalDateTime.now();
    }

    public Tale createTale(List<String> content) {
        return Tale.builder()
                .content(content.get(0))
                .kor(content.get(1))
                .title(content.get(2))
                .build();
    }

    public void addTaleKeyword(TaleKeyword newTaleKeyword) {
        this.taleKeywords.add(newTaleKeyword);
    }

}

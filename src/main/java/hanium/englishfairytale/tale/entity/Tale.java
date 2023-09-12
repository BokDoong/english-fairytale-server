package hanium.englishfairytale.tale.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Tale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TALE_ID")
    private Long id;
    @Column(name = "TALE_TITLE", length = 100)
    private String title;
    @Column(name = "TALE_CONTENT", length = 5000)
    private String content;
    @Column(name = "TALE_KOR",length = 3000)
    private String kor;
    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime created_at;


    @OneToMany(mappedBy = "tale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaleKeywords> taleKeywords = new ArrayList<>();
}

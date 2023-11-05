package hanium.englishfairytale.post.domain;

import hanium.englishfairytale.tale.domain.Tale;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tale_id")
    private Tale tale;

    public Likes(Long memberId) {
        this.memberId = memberId;
    }

    public void setPost(Tale tale) {
        this.tale = tale;
    }
}

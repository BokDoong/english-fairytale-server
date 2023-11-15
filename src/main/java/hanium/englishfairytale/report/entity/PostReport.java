package hanium.englishfairytale.report.entity;

import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tale_id")
    private Tale tale;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reporter_id")
    private Member member;
    @Column(name = "created_date")
    private LocalDateTime createdTime;
}

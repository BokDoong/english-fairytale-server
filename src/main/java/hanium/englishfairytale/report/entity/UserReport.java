package hanium.englishfairytale.report.entity;

import hanium.englishfairytale.member.domain.Member;
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
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reported_id")
    private Member reported;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reporter_id")
    private Member reporter;
    @Column(name = "created_date")
    private LocalDateTime createdTime;
}

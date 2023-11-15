package hanium.englishfairytale.report.repository;

import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.report.entity.PostReport;
import hanium.englishfairytale.tale.domain.Tale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {
//    public Long save(PostReport postReport);
    Optional<PostReport> findByMemberAndTale(Member member, Tale tale);
}

package hanium.englishfairytale.tale.repository;

import hanium.englishfairytale.tale.entity.Tale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaleRepository extends JpaRepository<Tale, Long> {
}

package hanium.englishfairytale.tale.domain;

import java.util.List;
import java.util.Optional;

public interface TaleRepository {

    void save(TaleKeyword taleKeyword);

    Optional<Keyword> findByWord(String word);

    void deleteByTaleId(Long taleId);
}

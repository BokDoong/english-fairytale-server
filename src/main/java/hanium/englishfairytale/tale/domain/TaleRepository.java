package hanium.englishfairytale.tale.domain;

import java.util.Optional;

public interface TaleRepository {

    void save(TaleKeyword taleKeyword);

    Optional<Keyword> findByWord(String word);

}

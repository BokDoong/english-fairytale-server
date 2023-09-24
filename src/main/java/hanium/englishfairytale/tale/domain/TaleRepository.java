package hanium.englishfairytale.tale.domain;

import java.util.List;
import java.util.Optional;

public interface TaleRepository {

    void save(TaleKeyword taleKeyword);

    Optional<Keyword> findByWord(String word);

    List<Tale> findTalesList(Long userId, int offset, int limit);

    Optional<Tale> findTaleDetailInfo(Long taleId);

}

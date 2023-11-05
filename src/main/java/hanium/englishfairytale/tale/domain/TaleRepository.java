package hanium.englishfairytale.tale.domain;

import java.util.List;
import java.util.Optional;

public interface TaleRepository {

    void save(TaleKeyword taleKeyword);

    Optional<Keyword> findByWord(String word);

    void deleteByTaleId(Long taleId);

    // 동화 목록조회(페이징)
    List<Tale> findTalesByMemberId(Long memberId, int offset, int limit);

    // 동화 단건조회
    Optional<Tale> findTaleByTaleId(Long taleId);

    // 키워드 조회(TaleKeywordId)
    List<Keyword> findKeywordByTaleId(Long taleId);

    // 동화개수 조회
    Long countTales(Long memberId);
}

package hanium.englishfairytale.member.application;

import hanium.englishfairytale.tale.application.dto.TalesInfo;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final TaleRepository taleRepository;

    @Transactional(readOnly = true)
    public List<TalesInfo> findMainTalesInfo(Long memberId) {

        // memberId -> Tale 조회 -> 최신순, Tale+Member+Image+TaleKeywords 나옴.
        // 각 TaleKeyword 의 id값 -> Keywords 조회 -> 위에서 찾은 TaleKeyword 의 id 값을 넣는다. ->


        return null;
    }

    private List<Keyword> getKeywordsFromTaleKeyword(List<TaleKeyword> taleKeywords) {
        return taleKeywords.stream()
                .map(TaleKeyword::getKeyword)
                .collect(Collectors.toList());
    }
}

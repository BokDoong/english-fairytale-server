package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import hanium.englishfairytale.tale.application.dto.response.TaleDetailInfo;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.factory.CreatedTale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaleCommandService {

    private final TaleRepository taleRepository;
    private final TaleManageService taleManageService;

    @Transactional
    public TaleDetailInfo create(TaleCreateCommand taleCreateCommand) {

        // 동화 제작
        CreatedTale createdTale = createGptEnglishTale(taleCreateCommand);

        // Tale, Keyword
        Tale tale = createTale(createdTale);
        List<Keyword> newKeywords = createKeywords(taleCreateCommand.getKeywords());

        // 동화 생성
        createAndSaveTaleKeyword(tale, newKeywords);

        return new TaleDetailInfo(tale, newKeywords);
    }

    private static Tale createTale(CreatedTale createdTale) {
        return Tale.builder()
                .build().createTale(createdTale);
    }

    private CreatedTale createGptEnglishTale(TaleCreateCommand taleCreateCommand) {
        verifyKeywords(taleCreateCommand);
        return taleManageService.post(taleCreateCommand.getModel(), taleCreateCommand.getKeywords());
    }

    private static void verifyKeywords(TaleCreateCommand taleCreateCommand) {
        Keyword.verifyNumberOfKeywords(taleCreateCommand.getKeywords());
        Keyword.verifyDuplicatedKeywords(taleCreateCommand.getKeywords());
    }

    private void createAndSaveTaleKeyword(Tale tale, List<Keyword> keywords) {
        for(Keyword keyword: keywords) {
            taleRepository.save(TaleKeyword.createTaleKeyword(tale, keyword));
        }
    }

    private List<Keyword> createKeywords(List<String> words) {
        return findAndCreateKeywords(words);
    }

    private List<Keyword> findAndCreateKeywords(List<String> words) {
        List<Keyword> keywords = new ArrayList<>();

        words.forEach(word -> {
            Optional<Keyword> optionalKeyword = taleRepository.findByWord(word);
            if (optionalKeyword.isEmpty()) {
                keywords.add(Keyword.builder().word(word).build());
            } else {
                keywords.add(optionalKeyword.get());
            }
        });

        return keywords;
    }
}

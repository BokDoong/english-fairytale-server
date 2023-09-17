package hanium.englishfairytale.tale.application.impl;

import hanium.englishfairytale.tale.application.ChatGptService;
import hanium.englishfairytale.tale.application.TaleCommandService;
import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import hanium.englishfairytale.tale.application.dto.response.TaleDetailInfo;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaleCommandServiceImpl implements TaleCommandService {

    private final ChatGptService chatGptService;
    private final TaleRepository taleRepository;

    @Override
    @Transactional
    public TaleDetailInfo create(TaleCreateCommand taleCreateCommand) {

        // 동화 제작
        List<String> content = createGptEnglishTale(taleCreateCommand);

        // Tale, Keyword
        Tale newTale = createTale(content);
        List<Keyword> newKeywords = createKeywords(taleCreateCommand.getKeywords());

        // 동화 생성
        createAndSaveTaleKeyword(newTale, newKeywords);

        return new TaleDetailInfo(newTale, newKeywords);
    }

    private static Tale createTale(List<String> content) {
        return Tale.builder()
                .build().createTale(content);
    }

    private List<String> createGptEnglishTale(TaleCreateCommand taleCreateCommand) {
        return chatGptService.post(taleCreateCommand.getModel(), taleCreateCommand.getKeywords());
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

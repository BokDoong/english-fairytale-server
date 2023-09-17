package hanium.englishfairytale.tale.application.impl;

import hanium.englishfairytale.tale.application.ChatGptService;
import hanium.englishfairytale.tale.application.TaleCommandService;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import hanium.englishfairytale.tale.infra.http.dto.request.TaleCreateDto;
import hanium.englishfairytale.tale.infra.http.dto.response.TaleCreateResponse;
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
    public TaleCreateResponse create(TaleCreateDto taleCreateDto) {

        // 동화 제작
        List<String> content = createGptEnglishTale(taleCreateDto);

        // Tale, Keyword
        Tale newTale = createTale(content);
        List<Keyword> newKeywords = createKeyword(taleCreateDto.getKeywords());

        // 동화 생성
        createAndSaveTaleKeyword(newTale, newKeywords);

        return new TaleCreateResponse(newTale, newKeywords);
    }

    private List<String> createGptEnglishTale(TaleCreateDto taleCreateDto) {
        return chatGptService.post(taleCreateDto.getModel(), taleCreateDto.getKeywords());
    }

    private void createAndSaveTaleKeyword(Tale tale, List<Keyword> keywords) {
        for(Keyword keyword: keywords) {
            taleRepository.save(TaleKeyword.createTaleKeyword(tale, keyword));
        }
    }

    private List<Keyword> createKeyword(List<String> words) {

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

    private Tale createTale(List<String> content) {
        return Tale.builder()
                .content(content.get(0))
                .kor(content.get(1))
                .title(content.get(2))
                .build();
    }
}

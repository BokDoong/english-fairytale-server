package hanium.englishfairytale.tale.application.impl;

import hanium.englishfairytale.tale.application.ChatGptService;
import hanium.englishfairytale.tale.application.TaleService;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import hanium.englishfairytale.tale.infra.http.dto.request.TaleCreateDto;
import hanium.englishfairytale.tale.infra.http.dto.response.TaleCreateResponse;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaleServiceImpl implements TaleService {

    private final ChatGptService chatGptService;
    private final TaleRepository taleRepository;

    @Override
    @Transactional
    public TaleCreateResponse create(TaleCreateDto taleCreateDto) {
        String model = taleCreateDto.getModel(); // 버전: "gpt-3.5-turbo"
        List<String> keywords = taleCreateDto.getKeywords(); // 키워드: ["key", "word"]

        // 동화 제작
        List<String> content = chatGptService.post(model, keywords);

        // Tale, Keyword
        Tale newTale = createTale(content);
        List<Keyword> newKeywords = createKeyword(keywords);

        // 동화 생성
        createAndSaveTaleKeyword(newTale, newKeywords);

        return new TaleCreateResponse(newTale, newKeywords);
    }

    private void createAndSaveTaleKeyword(Tale newTale, List<Keyword> newKeywords) {
        for(Keyword newKeyword: newKeywords) {
            TaleKeyword newtaleKeyword = TaleKeyword.createTaleKeyword(newTale, newKeyword);
            taleRepository.save(newtaleKeyword);
        }
    }

    private List<Keyword> createKeyword(List<String> keywords) {
        return keywords.stream()
                .map(word -> Keyword.builder().word(word).build())
                .collect(Collectors.toList());
    }

    private Tale createTale(List<String> content) {
        return Tale.builder()
                .content(content.get(0))
                .kor(content.get(1))
                .title(content.get(2))
                .build();
    }
}

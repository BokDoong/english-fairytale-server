package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.domain.factory.CreatedTale;
import hanium.englishfairytale.tale.domain.factory.TaleFactory;
import hanium.englishfairytale.tale.domain.factory.dto.CreateTaleDto;
import hanium.englishfairytale.tale.domain.factory.TaleUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaleManageService {

    private final TaleFactory taleFactory;

    // TODO: GPT 한테 json 형태로 받아오기
    public CreatedTale post(String model, List<String> keywords) {

        // 키워드 -> 동화 제작 질문 작성
        List<CreateTaleDto> engTaleMessages = TaleUtility.createEngTaleMessages(keywords);
        // 영어동화 생성
        String engTale = taleFactory.getGptResponse(model, engTaleMessages);

        // 한글 번역본 질문 작성
        List<CreateTaleDto> korTaleMessages = TaleUtility.createKorTaleMessages(engTale);
        // 번역본 생성
        String korTale = taleFactory.getGptResponse(model, korTaleMessages);

        // 제목 질문 생성
        List<CreateTaleDto> taleTitleMessages = TaleUtility.createTaleTitleMessages(engTale);
        // 질문 생성
        String title = taleFactory.getGptResponse(model, taleTitleMessages);

        return new CreatedTale(title, engTale, korTale);
    }
}

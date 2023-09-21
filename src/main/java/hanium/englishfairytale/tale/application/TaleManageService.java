package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.tale.domain.factory.CreatedTale;
import hanium.englishfairytale.tale.domain.factory.TaleFactory;
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

        // 키워드 -> 동화 제작 질문 작성 및 생성
        String engTale = taleFactory.getGptResponse(model, TaleUtility.createEngTaleMessages(keywords));
        // 한글 번역본 질문 작성 및 생성
        String korTale = taleFactory.getGptResponse(model, TaleUtility.createKorTaleMessages(engTale));
        // 제목 질문 작성 및 생성
        String title = taleFactory.getGptResponse(model, TaleUtility.createTaleTitleMessages(engTale));

        return new CreatedTale(title, engTale, korTale);
    }
}

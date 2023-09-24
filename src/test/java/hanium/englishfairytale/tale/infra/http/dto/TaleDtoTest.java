package hanium.englishfairytale.tale.infra.http.dto;

import hanium.englishfairytale.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TaleDtoTest {

    @Test
    void checkKeywordLimitException() {

        List<String> testKeywords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testKeywords.add("테스트 키워드");
        }

        Assertions.assertThrows(BusinessException.class, () -> {
            TaleCreateDto testTaleCreateDto = new TaleCreateDto(12L,"테스트 모델", testKeywords);
        });
    }
}

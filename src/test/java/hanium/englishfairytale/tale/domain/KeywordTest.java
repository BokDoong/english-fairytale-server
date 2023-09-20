package hanium.englishfairytale.tale.domain;


import hanium.englishfairytale.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KeywordTest {

    @Test
    void 키워드_개수초과_검사() {
        List<String> testKeywords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testKeywords.add("테스트 키워드");
        }

        Assertions.assertThrows(BusinessException.class, () -> {
            Keyword.verifyNumberOfKeywords(testKeywords);
        });
    }

    @Test
    void 중복_키워드_검사() {
        List<String> testKeywords = List.of("진", "이진", "복둥", "이진");

        Assertions.assertThrows(BusinessException.class, () -> {
            Keyword.verifyDuplicatedKeywords(testKeywords);
        });
    }
}

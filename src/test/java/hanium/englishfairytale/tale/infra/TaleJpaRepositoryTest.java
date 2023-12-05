package hanium.englishfairytale.tale.infra;

import hanium.englishfairytale.tale.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@DataJpaTest
//class TaleJpaRepositoryTest {
//    @Autowired
//    private TaleRepository taleRepository;
//
//    @Test
//    void 멤버_동화조회() {
//        // given
//        TaleImage taleImage = TaleImage.builder()
//                .imageUrl("~~qwjifa")
//                .build();
//        Tale tale = Tale.builder()
//                .engTale("Test Tale")
//                .build();
//        tale.putImage(taleImage);
//        Keyword keyword1 = new Keyword("키워드1");
//        Keyword keyword2 = new Keyword("키워드2");
//
//        TaleKeyword taleKeyword1 = TaleKeyword.createTaleKeyword(tale, keyword1);
//
//        // when
//        taleRepository.save(taleKeyword1);
//
//        // then
//        Assertions.assertThat(taleKeyword1).isEqualTo(taleRepository.findTaleKeywordByTaleId(tale.getId()).get(0));
//    }
//
//    @Test
//    void findTaleDetailInfo() {
//    }
//}
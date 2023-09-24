package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.tale.application.dto.request.TaleCreateCommand;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import hanium.englishfairytale.tale.application.dto.response.TaleCreateResponse;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.factory.CreatedTale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaleCommandService {

    private final MemberRepository memberRepository;
    private final TaleRepository taleRepository;
    private final TaleManageService taleManageService;
    private final FileManageService fileManageService;

    @Transactional
    public TaleCreateResponse create(TaleCreateCommand taleCreateCommand) {
        Tale tale = createTale(taleCreateCommand);
        List<Keyword> keywords = createKeywords(taleCreateCommand);
        String imageUrl = saveTaleKeywordAndGetImageUrl(tale, keywords, taleCreateCommand.getImage());

        return new TaleCreateResponse(tale, keywords, imageUrl);
    }

    private Tale createTale(TaleCreateCommand taleCreateCommand) {
        Member member = findMember(taleCreateCommand.getMemberId());
        CreatedTale createdTale = createEnglishTale(taleCreateCommand);
        return Tale.builder()
                .title(createdTale.getTitle())
                .engTale(createdTale.getEngTale())
                .korTale(createdTale.getKorTale())
                .member(member)
                .build();
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, memberId));
    }

    private CreatedTale createEnglishTale(TaleCreateCommand taleCreateCommand) {
        verifyKeywords(taleCreateCommand);
        return taleManageService.post(taleCreateCommand.getModel(), taleCreateCommand.getKeywords());
    }

    private void verifyKeywords(TaleCreateCommand taleCreateCommand) {
        Keyword.verifyNumberOfKeywords(taleCreateCommand.getKeywords());
        Keyword.verifyDuplicatedKeywords(taleCreateCommand.getKeywords());
    }

    private String saveTaleKeywordAndGetImageUrl(Tale tale, List<Keyword> keywords, MultipartFile image) {
        for(Keyword keyword: keywords) {
            taleRepository.save(TaleKeyword.createTaleKeyword(tale, keyword));
        }
        return saveAndGetImageUrl(tale, image);
    }

    private String saveAndGetImageUrl(Tale tale, MultipartFile image) {
        if (image == null) {
            return null;
        }

        return fileManageService.uploadImage(tale, image);
    }

    private List<Keyword> createKeywords(TaleCreateCommand taleCreateCommand) {
        return findAndCreateKeywords(taleCreateCommand.getKeywords());
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

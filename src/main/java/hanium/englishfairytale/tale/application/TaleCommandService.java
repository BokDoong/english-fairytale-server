package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.common.files.FileManageService;
import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.tale.application.dto.TaleCreateCommand;
import hanium.englishfairytale.tale.application.dto.TaleUpdateCommand;
import hanium.englishfairytale.tale.domain.*;
import hanium.englishfairytale.tale.application.dto.TaleCreateResponse;
import hanium.englishfairytale.tale.domain.factory.CreatedTale;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
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
    private final ImageRepository imageRepository;
    private final TaleQueryDao taleQueryDao;
    private final TaleManageService taleManageService;
    private final FileManageService fileManageService;

    @Transactional
    public TaleCreateResponse create(TaleCreateCommand taleCreateCommand) {
        Tale tale = createTale(taleCreateCommand);
        List<Keyword> keywords = findAndCreateKeywords(taleCreateCommand);
        return saveTaleAndKeywords(tale, keywords, taleCreateCommand.getImage());
    }

    @Transactional
    public void deleteTale(Long taleId) {
        verifyExistedTale(taleId);
        taleRepository.deleteByTaleId(taleId);
    }

    @Transactional
    public void updateTaleImage(TaleUpdateCommand taleUpdateCommand) {
        Tale tale = findTale(taleUpdateCommand.getTaleId());
        if (!verifyUpdateImageEmpty(taleUpdateCommand)) {
            tale.updateTaleImage(saveTaleImage(taleUpdateCommand.getImage()));
        } else {
            tale.deleteTaleImage(taleUpdateCommand.getTaleId());
        }
    }

    private boolean verifyUpdateImageEmpty(TaleUpdateCommand taleUpdateCommand) {
        return taleUpdateCommand.getImage().isEmpty();
    }

    private Tale findTale(Long taleId) {
        return taleQueryDao.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }

    private void verifyExistedTale(Long taleId) {
        taleQueryDao.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
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
        verifyInputKeywords(taleCreateCommand);
        return taleManageService.post(taleCreateCommand.getModel(), taleCreateCommand.getKeywords());
    }

    private void verifyInputKeywords(TaleCreateCommand taleCreateCommand) {
        Keyword.verifyNumberOfKeywords(taleCreateCommand.getKeywords());
        Keyword.verifyDuplicatedKeywords(taleCreateCommand.getKeywords());
    }

    private TaleCreateResponse saveTaleAndKeywords(Tale tale, List<Keyword> keywords, MultipartFile image) {
        if (!image.isEmpty()) {
            tale.putImage(saveTaleImage(image));
        }
        saveTaleKeywords(tale, keywords);
        return new TaleCreateResponse(tale,keywords);
    }

    private void saveTaleKeywords(Tale tale, List<Keyword> keywords) {
        for(Keyword keyword: keywords) {
            taleRepository.save(TaleKeyword.createTaleKeyword(tale, keyword));
        }
    }

    private TaleImage saveTaleImage(MultipartFile image) {
        return new TaleImage(fileManageService.uploadTaleImage(image));
    }

    private List<Keyword> findAndCreateKeywords(TaleCreateCommand taleCreateCommand) {
        List<Keyword> keywords = new ArrayList<>();

        taleCreateCommand.getKeywords().forEach(word -> {
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

package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.tale.application.dto.TaleDetailInfo;
import hanium.englishfairytale.tale.application.dto.TalesInfo;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaleQueryService {

    private final TaleRepository taleRepository;
    private final MemberRepository memberRepository;

    // 동화 상세조회
    @Transactional(readOnly = true)
    public TaleDetailInfo findDetailTale(Long taleId) {
        Tale tale = findTale(taleId);
        return convertTaleToDetailInfo(tale);
    }

    // 동화 전체조회
    @Transactional(readOnly = true)
    public List<TalesInfo> findAllTales(Long memberId, int offset) {
        verifyExistedMember(memberId);
        List<Tale> tales = findAllTales(memberId, offset, 20);
        return convertTaleToTaleInfos(memberId, tales);
    }

    // 메인 페이지(5개 조회)
    @Transactional(readOnly = true)
    public List<TalesInfo> findRecentTales(Long memberId) {
        verifyExistedMember(memberId);
        List<Tale> tales = findAllTales(memberId, 0, 5);
        return convertTaleToTaleInfos(memberId, tales);
    }

    private Tale findTale(Long taleId) {
        return taleRepository.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }

    private TaleDetailInfo convertTaleToDetailInfo(Tale tale) {
        return new TaleDetailInfo(tale, findKeywords(tale));
    }

    private void verifyExistedMember(Long memberId) {
        if (memberRepository.findMemberById(memberId).isEmpty()) {
            throw new NotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }

    private List<TalesInfo> convertTaleToTaleInfos(Long memberId, List<Tale> tales) {
        return tales.stream()
                .map(tale -> new TalesInfo(memberId, tale, findKeywords(tale)))
                .collect(Collectors.toList());
    }

    private List<Keyword> findKeywords(Tale tale) {
        return taleRepository.findKeywordByTaleId(tale.getId());
    }

    private List<Tale> findAllTales(Long memberId, int offset, int limit) {
        List<Tale> tales = taleRepository.findTalesByMemberId(memberId, offset, limit);
        if (tales.isEmpty()) {
            throw new NotFoundException(ErrorCode.TALE_NOT_CREATED);
        }
        return tales;
    }
}

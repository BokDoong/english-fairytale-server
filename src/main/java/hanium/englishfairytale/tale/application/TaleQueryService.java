package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.MemberRepository;
import hanium.englishfairytale.tale.application.dto.response.TaleDetailInfo;
import hanium.englishfairytale.tale.application.dto.response.TalesInfo;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaleQueryService {

    private final TaleQueryDao taleQueryDao;
    private final MemberRepository memberRepository;

    // 동화 상세조회
    @Transactional(readOnly = true)
    public TaleDetailInfo findDetailTale(Long taleId) {
        Tale tale = findTale(taleId);
        return convertTaleToDetailInfo(tale);
    }

    // 마이 페이지(전체 조회)
    @Transactional(readOnly = true)
    public List<TalesInfo> findAllTales(Long memberId, int offset) {
        verifyExistedMember(memberId);
        List<Tale> tales = findAllTales(memberId, offset, 20);
        return convertTaleToTaleInfos(tales);
    }

    // 메인 페이지(5개 조회)
    @Transactional(readOnly = true)
    public List<TalesInfo> findRecentTales(Long memberId) {
        verifyExistedMember(memberId);
        List<Tale> tales = findAllTales(memberId, 0, 5);
        return convertTaleToTaleInfos(tales);
    }

    private Tale findTale(Long taleId) {
        return taleQueryDao.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }

    private TaleDetailInfo convertTaleToDetailInfo(Tale tale) {
        return new TaleDetailInfo(tale, findKeywords(tale));
    }

    private void verifyExistedMember(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()) {
            throw new NotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }

    private List<TalesInfo> convertTaleToTaleInfos(List<Tale> tales) {
        return tales.stream()
                .map(tale -> new TalesInfo(tale, findKeywords(tale)))
                .collect(Collectors.toList());
    }

    private List<Keyword> findKeywords(Tale tale) {
        return taleQueryDao.findKeywordByTaleId(tale.getId());
    }

    private List<Tale> findAllTales(Long memberId, int offset, int limit) {
        List<Tale> tales = taleQueryDao.findTalesByMemberId(memberId, offset, limit);
        if (tales.isEmpty()) {
            throw new NotFoundException(ErrorCode.TALE_NOT_CREATED);
        }
        return tales;
    }
}

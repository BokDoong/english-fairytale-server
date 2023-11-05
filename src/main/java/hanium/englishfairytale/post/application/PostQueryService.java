package hanium.englishfairytale.post.application;

import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import hanium.englishfairytale.post.domain.PostRepository;
import hanium.englishfairytale.post.infra.PostQueryDao;
import hanium.englishfairytale.post.infra.dto.PostLikesQueryDto;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final TaleRepository taleRepository;
    private final PostRepository postRepository;
    private final PostQueryDao postQueryDao;

    // TODO: 단일책임원칙 고려한 refactoring 필요
    @Transactional
    public List<PostedTalesInfo> findPostsByLikes(int offset, Long memberId) {
        // 포스팅된 동화들 조회
        List<PostedTalesInfo> postedTalesInfos = findPostedTaleInfos(offset, memberId);
        // 좋아요 조회 및 동화별로 그루핑
        Map<Long, List<PostLikesQueryDto>> postLikesMap = findPostLikesMap(postedTalesInfos);
        // 좋아요수 꼽아줌
        postedTalesInfos.forEach(info -> info.setLikeCounts((postLikesMap.get(info.getTaleId()) == null) ? 0 : postLikesMap.get(info.getTaleId()).size()));
        // 키워드 찾아서 꼽아줌
        postedTalesInfos.forEach(info -> info.setKeywordContents(taleRepository.findKeywordByTaleId(info.getTaleId())));
        // 좋아요순 정렬
        return Tale.sortPostedTalesByLikes(postedTalesInfos);
    }

    // TODO: 키워드 조회 쿼리 최적화
    @Transactional
    public List<PostedTalesInfo> findPostsByDate(int offset, Long memberId) {
        List<Tale> tales = findPostsSortedByDate(offset);
        return convertPostsToPostInfosWithMemberId(tales, memberId);
    }

    @Transactional
    public List<PostedTalesInfo> findPostsForMyPage(int offset, Long memberId) {
        List<Tale> tales = findPostedTalesByMemberId(offset, memberId);
        return convertPostsToPostInfosWithMemberId(tales, memberId);
    }

    @Transactional
    public List<PostedTalesInfo> findLikedPostsForMyPage(int offset, Long memberId) {
        List<Tale> tales = findLikedPostedTalesByMemberId(offset, memberId);
        return convertLikedPostsToPostInfos(tales);
    }

    @Transactional
    public List<PostedTalesInfo> findPostedTalesByTitle(int offset, String title, Long memberId) {
        List<Tale> tales = postRepository.findPostedTaleListByTitle(offset, 20, title);
        return convertPostsToPostInfosWithMemberId(tales, memberId);
    }

    private List<Tale> findLikedPostedTalesByMemberId(int offset, Long memberId) {
        return postRepository.findLikedPostedTaleListByMemberId(memberId, offset, 20);
    }

    private List<Tale> findPostedTalesByMemberId(int offset, Long memberId) {
        return postRepository.findPostedTaleListByMemberId(memberId, offset, 20);
    }

    private Map<Long, List<PostLikesQueryDto>> findPostLikesMap(List<PostedTalesInfo> postedTalesInfos) {
        return postQueryDao.findPostLikesMap(Tale.toTaleIds(postedTalesInfos));
    }

    private List<PostedTalesInfo> findPostedTaleInfos(int offset, Long memberId) {
        List<Tale> tales = postRepository.findPostedTaleList(offset);
        return Tale.toTalesInfo(tales, memberId);
    }

    private List<Tale> findPostsSortedByDate(int offset) {
        return postRepository.findPostedTalesSortedByDate(offset, 20);
    }

    private List<PostedTalesInfo> convertLikedPostsToPostInfos(List<Tale> tales) {
        return tales.stream()
                .map(tale -> new PostedTalesInfo(tale, tale.countLikes(), findKeywords(tale), true))
                .collect(Collectors.toList());
    }

    private List<PostedTalesInfo> convertPostsToPostInfosWithMemberId(List<Tale> tales, Long memberId) {
        return tales.stream()
                .map(tale -> new PostedTalesInfo(tale, tale.countLikes(), findKeywords(tale), tale.checkMemberLikedPost(memberId)))
                .collect(Collectors.toList());
    }

    private List<Keyword> findKeywords(Tale tale) {
        return taleRepository.findKeywordByTaleId(tale.getId());
    }

}

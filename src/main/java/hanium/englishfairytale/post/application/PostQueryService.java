package hanium.englishfairytale.post.application;

import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import hanium.englishfairytale.post.domain.PostRepository;
import hanium.englishfairytale.post.infra.PostQueryDao;
import hanium.englishfairytale.post.infra.dto.PostLikesQueryDto;
import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final TaleQueryDao taleQueryDao;
    private final PostRepository postRepository;
    private final PostQueryDao postQueryDao;

    @Transactional
    public List<PostedTalesInfo> findPostsByLikes(int offset) {
        // 포스팅된 동화들 조회
        List<PostedTalesInfo> postedTalesInfos = findPostedTales(offset);
        // 좋아요 조회 및 동화별로 그루핑
        Map<Long, List<PostLikesQueryDto>> postLikesMap = findPostLikesMap(postedTalesInfos);
        // 좋아요수 꼽아줌
        postedTalesInfos.forEach(info -> info.setLikeCounts((postLikesMap.get(info.getTaleId()) == null) ? 0 : postLikesMap.get(info.getTaleId()).size()));
        // 키워드 찾아서 꼽아줌
        postedTalesInfos.forEach(info -> info.setKeywordContents(taleQueryDao.findKeywordByTaleId(info.getTaleId())));
        // 좋아요순 정렬
        return Tale.sortPostedTalesByLikes(postedTalesInfos);
    }

    @Transactional
    public List<PostedTalesInfo> findPostsByDate(int offset) {
        List<Tale> tales = findPostsSortedByDate(offset);
        return convertPostsToPostInfos(tales);
    }

    private Map<Long, List<PostLikesQueryDto>> findPostLikesMap(List<PostedTalesInfo> postedTalesInfos) {
        return postQueryDao.findPostLikesMap(Tale.toTaleIds(postedTalesInfos));
    }

    private List<PostedTalesInfo> findPostedTales(int offset) {
        return postQueryDao.findPostedTales(offset, 20);
    }

    private List<Tale> findPostsSortedByDate(int offset) {
        return postRepository.findPostedTalesSortedByDate(offset, 20);
    }

    private List<PostedTalesInfo> convertPostsToPostInfos(List<Tale> tales) {
        return tales.stream()
                .map(tale -> new PostedTalesInfo(tale, tale.countLikes(), findKeywords(tale)))
                .collect(Collectors.toList());
    }

    private List<Keyword> findKeywords(Tale tale) {
        return taleQueryDao.findKeywordByTaleId(tale.getId());
    }

}

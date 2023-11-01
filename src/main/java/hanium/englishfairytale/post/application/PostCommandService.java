package hanium.englishfairytale.post.application;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.post.domain.PostRepository;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final TaleQueryDao taleQueryDao;

    @Transactional
    public void post(Long taleId) {
        Tale tale = findTale(taleId);
        postTale(tale);
    }

    @Transactional
    public void postTale(Tale tale) {
        tale.verifyPostAlreadyExisted();
        tale.posting();
    }

    @Transactional
    public void deletePost(Long taleId) {
        Tale tale = findTale(taleId);
        tale.verifyPostNotExisted();
        tale.deletePosting();
    }

    @Transactional
    public boolean updateLikes(Long memberId, Long taleId) {
        Tale tale = findTaleWithLikes(taleId);
        tale.verifyPostNotExisted();
        return tale.updateLike(memberId);
    }

    private Tale findTaleWithLikes(Long taleId) {
        return postRepository.findTaleWithLikes(taleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TALE_NOT_FOUND));
    }

    private Tale findTale(Long taleId) {
        return taleQueryDao.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }


}

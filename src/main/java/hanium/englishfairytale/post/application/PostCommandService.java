package hanium.englishfairytale.post.application;

import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.infra.MemberQueryDao;
import hanium.englishfairytale.post.domain.Post;
import hanium.englishfairytale.post.domain.PostRepository;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.infra.TaleQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final MemberQueryDao memberQueryDao;
    private final TaleQueryDao taleQueryDao;
    private final PostRepository postRepository;

    @Transactional
    public Long create(Long memberId, Long taleId) {
        Member member = findMemberById(memberId);
        Tale tale = findTaleById(taleId);
        tale.verifyPostAlreadyExisted();
        return createAndSavePost(tale, member);
    }

    private Long createAndSavePost(Tale tale, Member member) {
        Post post = Post.builder()
                .member(member).tale(tale).build();

        postRepository.save(post);
        return post.getId();
    }

    private Tale findTaleById(Long taleId) {
        return taleQueryDao.findTaleByTaleId(taleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TALE_NOT_FOUND));
    }

    private Member findMemberById(Long memberId) {
        return memberQueryDao.findMemberAndImage(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }
}

package hanium.englishfairytale.post.infra;

import hanium.englishfairytale.post.domain.Post;
import hanium.englishfairytale.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostJpaRepository implements PostRepository {

    private final EntityManager em;

    @Override
    public void save(Post post) {
        em.persist(post);
    }
}

package hanium.englishfairytale.post.infra.http;

import hanium.englishfairytale.post.application.PostCommandService;
import hanium.englishfairytale.post.application.PostQueryService;
import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostQueryService postQueryService;
    private final PostCommandService postCommandService;

    @PostMapping("/create")
    public void createPost(@RequestParam Long taleId) {
        postCommandService.post(taleId);
    }

    @PostMapping("/delete")
    public void deletePost(@RequestParam Long taleId) {
        postCommandService.deletePost(taleId);
    }

    @GetMapping("/like")
    public List<PostedTalesInfo> findPostsByLikes(@RequestParam int offset, @RequestParam Long memberId) {
        return postQueryService.findPostsByLikes(offset, memberId);
    }

    @PostMapping("/like")
    public boolean likePost(@RequestParam Long memberId, @RequestParam Long taleId) {
        return postCommandService.updateLikes(memberId, taleId);
    }

    @GetMapping("/date")
    public List<PostedTalesInfo> findPostsByDate(@RequestParam int offset, @RequestParam Long memberId) {
        return postQueryService.findPostsByDate(offset, memberId);
    }
}

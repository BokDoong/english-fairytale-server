package hanium.englishfairytale.post.infra.http;

import hanium.englishfairytale.post.application.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostCommandService postCommandService;

    @PostMapping("/create")
    public Long createPost(@RequestParam Long memberId, @RequestParam Long taleId) {
        return postCommandService.create(memberId, taleId);
    }
}

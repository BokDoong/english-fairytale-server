package hanium.englishfairytale.announcement.infra.http;

import hanium.englishfairytale.announcement.application.AnnouncementQueryService;
import hanium.englishfairytale.announcement.application.dto.AnnouncementResponse;
import hanium.englishfairytale.announcement.domain.AnnouncementType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementQueryService announcementQueryService;

    @GetMapping("")
    public List<AnnouncementResponse> findAnnouncements(@RequestParam String type) {
        return announcementQueryService.findAnnouncements(toEnum(type));
    }

    private AnnouncementType toEnum(String type) {
        return AnnouncementType.of(type);
    }
}

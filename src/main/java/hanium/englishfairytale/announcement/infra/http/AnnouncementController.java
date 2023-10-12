package hanium.englishfairytale.announcement.infra.http;

import hanium.englishfairytale.announcement.application.AnnouncementQueryService;
import hanium.englishfairytale.announcement.application.dto.AnnouncementResponse;
import hanium.englishfairytale.announcement.domain.AnnouncementType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementQueryService announcementQueryService;

    @GetMapping("")
    public ResponseEntity<List<AnnouncementResponse>> findAnnouncements(@RequestParam String type) {
        return new ResponseEntity<>(announcementQueryService.findAnnouncements(toEnum(type)), HttpStatus.OK);
    }

    private AnnouncementType toEnum(String type) {
        return AnnouncementType.of(type);
    }
}

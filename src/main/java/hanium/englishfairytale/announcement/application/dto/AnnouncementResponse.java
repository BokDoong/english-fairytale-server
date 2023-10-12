package hanium.englishfairytale.announcement.application.dto;

import hanium.englishfairytale.announcement.domain.Announcement;
import lombok.Getter;

@Getter
public class AnnouncementResponse {
    private String title;
    private String content;

    public AnnouncementResponse(Announcement announcement) {
        this.title = announcement.getTitle();
        this.content = announcement.getContent();
    }
}

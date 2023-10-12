package hanium.englishfairytale.announcement.domain;

import java.util.List;

public interface AnnouncementRepository {
    List<Announcement> findByType(AnnouncementType announcementType);
}

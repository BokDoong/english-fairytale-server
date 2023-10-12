package hanium.englishfairytale.announcement.application;

import hanium.englishfairytale.announcement.application.dto.AnnouncementResponse;
import hanium.englishfairytale.announcement.domain.Announcement;
import hanium.englishfairytale.announcement.domain.AnnouncementRepository;
import hanium.englishfairytale.announcement.domain.AnnouncementType;
import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementQueryService {

    private final AnnouncementRepository announcementRepository;

    @Transactional(readOnly = true)
    public List<AnnouncementResponse> findAnnouncements(AnnouncementType announcementType) {
        return convertToAnnouncementResponse(findAnnouncementsByType(announcementType));
    }

    private List<Announcement> findAnnouncementsByType(AnnouncementType announcementType) {
        List<Announcement> announcements = announcementRepository.findByType(announcementType);
        verifyAnnouncementsIsEmpty(announcements);
        return announcements;
    }

    public void verifyAnnouncementsIsEmpty(List<Announcement> announcements) {
        if (announcements.isEmpty()) {
            throw new NotFoundException(ErrorCode.ANNOUNCE_NOT_FOUND);
        }
    }

    private List<AnnouncementResponse> convertToAnnouncementResponse(List<Announcement> announcements) {
        return announcements.stream().map(AnnouncementResponse::new).collect(Collectors.toList());
    }

}

package hanium.englishfairytale.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnnouncementCode {
    ANNOUNCEMENT_NOT_FOUND("A-001"),
    NON_ANNOUNCEMENT_TYPE("A-002"),
    ;

    private final String code;
}

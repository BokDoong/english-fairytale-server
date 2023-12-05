package hanium.englishfairytale.announcement.domain;

import hanium.englishfairytale.exception.NotFoundException;
import hanium.englishfairytale.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public enum AnnouncementType {
    NOTICE("공지사항"),
    FAQ("FAQ"),
    TERMS_CONDITION("이용약관"),
    PRIVACY_POLICY("개인정보 처리방침");

    private final String type;

    AnnouncementType(String type) {
        this.type = type;
    }

    public static AnnouncementType of(String typeStr) {
        if (typeStr == null) {
            throw new IllegalStateException();
        }
        for (AnnouncementType at : AnnouncementType.values()) {
            if (at.type.equals(typeStr)) {
                return at;
            }
        }
        throw new NotFoundException(ErrorCode.ANNOUNCEMENT_TYPE_NOT_FOUND);
    }
}

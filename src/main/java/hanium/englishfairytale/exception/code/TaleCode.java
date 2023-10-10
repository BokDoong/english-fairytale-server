package hanium.englishfairytale.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaleCode {

    NOT_FOUND("T-001"),
    KEYWORD_COUNT_LIMIT("T-002"),
    KEYWORD_DUPLICATED("T-003"),
    IMAGE_IO("T-004"),
    NON_CREATED("T-005"),
    NON_EXISTED_IMAGE("T-006"),
    NON_IMAGE_STATUS("T-007"),
    ;

    private final String code;
}

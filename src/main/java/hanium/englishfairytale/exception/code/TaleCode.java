package hanium.englishfairytale.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaleCode {

    NOT_FOUND("T-001"),
    KEYWORD_COUNT_LIMIT("T-002"),
    KEYWORD_DUPLICATED("T-003")
    ;

    private final String code;
}

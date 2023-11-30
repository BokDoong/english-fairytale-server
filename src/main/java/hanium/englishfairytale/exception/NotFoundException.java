package hanium.englishfairytale.exception;

import hanium.englishfairytale.exception.code.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

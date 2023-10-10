package hanium.englishfairytale.tale.domain;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;

public enum ImageStatus {
    CUSTOMIZED("사용자 지정 이미지"),
    BASIC_FIRST("기본 이미지1"),
    BASIC_SECOND("기본 이미지2"),
    BASIC_THIRD("기본 이미지3"),
    BASIC_FOURTH("기본 이미지4"),
    BASIC_FIFTH("기본 이미지5");

    private final String status;

    ImageStatus(String status) {
        this.status = status;
    }

    public static ImageStatus of(String statusStr) {
        if (statusStr == null) {
            throw new IllegalStateException();
        }
        for (ImageStatus is : ImageStatus.values()) {
            if (is.status.equals(statusStr)) {
                return is;
            }
        }
        throw new BusinessException(ErrorCode.IMAGE_STATUS_NOT_FOUND);
    }

    public String getStatus() {
        return this.status;
    }
}

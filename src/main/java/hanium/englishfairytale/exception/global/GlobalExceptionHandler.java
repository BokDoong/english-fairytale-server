package hanium.englishfairytale.exception.global;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.ErrorResponse;
import hanium.englishfairytale.exception.RuntimeIOException;
import hanium.englishfairytale.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.BindException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 비즈니스 예외 처리시 발생
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    // S3 이미지 저장시 발생하는 예외 처리
    @ExceptionHandler(RuntimeIOException.class)
    protected ResponseEntity<ErrorResponse> bindException(RuntimeIOException e) {
        log.error("handleRuntimeIOException", e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    // javax.validation.Valid or @Validated 으로 binding error 발생시 발생
    // HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentValidation(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_REQUEST_PARAMETER);
    }

    // @ModelAttribute 으로 바인딩 에러시 발생
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> bindException(BindException e) {
        log.error("handleBindException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_REQUEST_PARAMETER);
    }

    // 지원하지 않은 HTTP Method 호출 할 경우 발생
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> requestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_METHOD_TYPE);
    }

    // JSON 형식 지키지 않았을 시 발생
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> invalidHttpMessageParsing(HttpMessageNotReadableException e) {
        log.error("handleHttpMessageNotReadableException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_JSON_TYPE);
    }

    // 이미지 크기 초과시 발생
    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class, MissingServletRequestPartException.class, MultipartException.class})
    protected ResponseEntity<ErrorResponse> imageFileSize(Exception e) {
        log.error("handleImageFileSizeException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.FILE_SIZE);
    }

    // 데이터 잘못 넘어갔을 경우 발생
    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException e) {
        log.error("handleIllegalArgumentException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_REQUEST_PARAMETER);
    }

    // 데이터 무결성 위반한 경우 발생
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("handleDataIntegrityViolationException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.DATA_INTEGRITY_VIOLATE);
    }

    // 나머지 에러 여기서 핸들링
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.SERVICE_UNAVAILABLE, e);
    }
}

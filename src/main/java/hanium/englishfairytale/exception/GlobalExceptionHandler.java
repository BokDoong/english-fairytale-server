package hanium.englishfairytale.exception;

import hanium.englishfairytale.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler
//    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e,
//                                                                    HttpServletRequest request) {
//        return createErrorResponse(e, request, e.getErrorCode());
//    }

    // javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
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

//    @ExceptionHandler
//    protected ResponseEntity<ErrorResponse> s3ImageSave(RuntimeIOException e,
//                                                        HttpServletRequest request) {
//        return createErrorResponse(e, request, e.getErrorCode());
//    }

//    @ExceptionHandler
//    protected ResponseEntity<ErrorResponse> responseStatus(ResponseStatusException e,
//                                                           HttpServletRequest request) {
//        return createErrorResponse(e, request, ErrorCode.INVALID_REQUEST_PARAMETER);
//    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException e) {
        log.error("handleIllegalArgumentException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_REQUEST_PARAMETER);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("handleDataIntegrityViolationException", e);
        return ErrorResponse.toResponseEntity(ErrorCode.DATA_INTEGRITY_VIOLATE);
    }

}

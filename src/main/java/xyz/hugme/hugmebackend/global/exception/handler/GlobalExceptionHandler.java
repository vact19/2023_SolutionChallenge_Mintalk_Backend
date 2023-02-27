package xyz.hugme.hugmebackend.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.dto.ErrorResponseDto;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("handleMethodArgumentNotValidException", e);
//        return createErrorResponse();
//    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     */
//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<ErrorResponseDto> handleBindException(BindException e, HttpServletRequest request) {
//        log.error("handleBindException", e);
//
//        BindingResult bindingResult = e.getBindingResult();
//        StringBuilder sb = new StringBuilder();
//        if(bindingResult.hasErrors()){
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage()).append("\n");
//            }
//        }
//
//        return exceptionResponseEntity(sb.toString(), HttpStatus.BAD_REQUEST, request.getRequestURI());
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e){
        log.error(e.getClass().getSimpleName() + ": 발생, 에러 메시지: "+ e.getMessage());
        return createErrorResponse(e.getStatusCode(), e.getHttpStatus(), e.getMessage());
    }

    private ResponseEntity<ErrorResponseDto> createErrorResponse(int statusCode, HttpStatus httpStatus, String errorMessage) {
        ErrorResponseDto errDto = new ErrorResponseDto(statusCode, httpStatus, errorMessage);
        return ResponseEntity.status(httpStatus).body(errDto);
    }


}


















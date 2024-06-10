package com.softlottery.lottery.shared.config.errorhandler;

import com.softlottery.lottery.shared.config.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(value = {BaseException.class})
  public ResponseEntity<ErrorResponseDto> handleBaseException(BaseException ex) {
    var error = ErrorResponseDto.builder()
        .key(ex.getKey())
        .message(ex.getMessage())
        .dateTime(LocalDateTime.now())
        .build();
    return ResponseEntity
        .status(ex.getStatusCode())
        .body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

    List<String> errors = e.getBindingResult().getFieldErrors().stream()
            .map(error ->error.getDefaultMessage()).toList();

    var error = ErrorResponseDto.builder()
            .message("Validation error")
            .errors(errors.toArray(new String[0]))
            .dateTime(LocalDateTime.now())
            .build();

    return ResponseEntity.badRequest().body(error);

  }

}

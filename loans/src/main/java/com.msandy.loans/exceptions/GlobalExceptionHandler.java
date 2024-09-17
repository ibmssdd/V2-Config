package com.msandy.loans.exceptions;

import com.msandy.loans.dtos.RespErrDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*@org.jetbrains.annotations.NotNull
//*@NotNull
@ControllerAdvice
//*@ExceptionHandler(CustomerAlreadyExistsException.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
//    protected ResponseEntity<Object> handleMethodArgumentNotValid()
//    {
//        return handleMethodArgumentNotValid(null, null, (HttpStatusCode) null, null);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request)
    {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) ->
        {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

@ExceptionHandler(Exception.class)
    public ResponseEntity<RespErrDto> handleGlobalException
            (@NotNull Exception exception, @NotNull WebRequest webRequest)
    {       RespErrDto respErrDto = new RespErrDto(webRequest.getDescription(false),
            HttpStatus.INTERNAL_SERVER_ERROR,
            exception.getMessage(),
            LocalDateTime.now());
        return new ResponseEntity<>(respErrDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
@ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<RespErrDto> handleCustomerAlreadyExistException
          (@NotNull CustomerAlreadyExistsException exception, @NotNull WebRequest webRequest)
    {      RespErrDto respErrDto = new RespErrDto(webRequest.getDescription(false),
           HttpStatus.BAD_REQUEST,
           exception.getMessage(),
           LocalDateTime.now()
          );

        return new ResponseEntity<>(respErrDto, HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RespErrDto> handleResourceNotFoundException
            (@NotNull ResourceNotFoundException exception, @NotNull WebRequest webRequest)
    {      RespErrDto respErrDto = new RespErrDto(webRequest.getDescription(false),
            HttpStatus.NOT_FOUND,
            exception.getMessage(),
            LocalDateTime.now());
        return new ResponseEntity<>(respErrDto, HttpStatus.NOT_FOUND);
    }

}


package com.thm.gw.controllers.advisors;

import com.thm.gw.exceptions.AlreadyExistsException;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.NotFoundException;
import com.thm.gw.exceptions.ResourceAccessDeniedException;
import com.thm.gw.exceptions.auth.AccountReactivatedException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AccountReactivatedException.class)
    public ResponseEntity<String> handleAccountReactivatedException(final AccountReactivatedException e){
        log.error(e.toString());

        return ResponseEntity.status(200).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequestException(final IllegalArgumentException e){
        log.error(e.toString());

        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpNotReadableException(final HttpMessageNotReadableException e){
        log.error(e.toString());

        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(final JwtException e){
        log.error(e.toString());

        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(final AccessDeniedException e) {
        log.error(e.toString());

        return ResponseEntity.status(403).body("Access denied !");
    }

    @ExceptionHandler(ResourceAccessDeniedException.class)
    public ResponseEntity<String> handleResourceAccessDeniedException(final ResourceAccessDeniedException e) {
        log.error(e.toString());

        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<String> handleNotAllowedException(final NotAllowedException e){
        log.error(e.toString());

        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(final UsernameNotFoundException e){
        log.error(e.toString());

        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(final AuthenticationException e){
        log.error(e.toString());

        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(final NoResourceFoundException e){
        log.error(e.toString());

        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(final NotFoundException e){
        log.error(e.toString());

        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(final AlreadyExistsException e){
        log.error(e.toString());

        return ResponseEntity.status(406).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e){
        Map<String,String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> Optional.of(fieldError.getField())
                                .orElse("No field provided."),
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                                .orElse("No error message provided."),
                        (existingValue, newValue) -> String.join(", ", existingValue, newValue)
                ));

        return ResponseEntity.status(406).body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(final ValidationException e){
        log.error(e.toString());

        return ResponseEntity.status(500).body(e.getMessage());
    }
}

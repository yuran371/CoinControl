package com.mergeteam.coincontrol.util.handler;

import com.mergeteam.coincontrol.util.UserErrorResponse;
import com.mergeteam.coincontrol.util.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found!",
                System.currentTimeMillis()
        );
        log.error("User with this id was'nt found!", e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
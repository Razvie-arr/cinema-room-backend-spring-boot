package cinema.exception;

import cinema.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSeatException.class)
    public ResponseEntity<ErrorMessage> handleInvalidSeat(InvalidSeatException e) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorMessage> handleSeatTaken(SeatNotAvailableException e) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ErrorMessage> handleWrongToken(WrongTokenException e) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorMessage> buildErrorResponse(Exception e, HttpStatus status) {
        return ResponseEntity.status(status).body(new ErrorMessage(e.getMessage()));
    }

}

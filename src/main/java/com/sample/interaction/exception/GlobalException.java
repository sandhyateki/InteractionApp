package com.sample.interaction.exception;

import com.sample.interaction.model.ErrorResponseTO;
import com.sample.interaction.model.ErrorTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalException {

    /**
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseTO> handleNullPointerException(final HttpServletRequest request,
                                                                    final NullPointerException exception) {
        ErrorResponseTO errorResponse = new ErrorResponseTO();
        final ErrorTO errorDTO = new ErrorTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                NullPointerException.class.getName());
        errorResponse.setError(errorDTO);
        log.error("Exception Occurred ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseTO> handleGenericException(final HttpServletRequest request,
                                                                final Exception exception) {
        ErrorResponseTO errorResponse = new ErrorResponseTO();
        final ErrorTO errorDTO = new ErrorTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getClass().toString());
        errorResponse.setError(errorDTO);
        log.error("Exception Occurred ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseTO> handleMethodNotSupported(final HttpServletRequest request,
                                                                  final HttpRequestMethodNotSupportedException exception) {
        ErrorResponseTO errorResponse = new ErrorResponseTO();
        final ErrorTO errorDTO = new ErrorTO(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED,
                request.getRequestURL().toString(), HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getClass().toString());
        errorResponse.setError(errorDTO);
        log.error("Exception Occurred ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseTO> handleBadRequest(final HttpServletRequest request,
                                                                    final HttpRequestMethodNotSupportedException exception) {
        ErrorResponseTO errorResponse = new ErrorResponseTO();
        final ErrorTO errorDTO = new ErrorTO(exception.getMessage(), HttpStatus.BAD_REQUEST,
                request.getRequestURL().toString(), HttpStatus.BAD_REQUEST.value(),
                exception.getClass().toString());
        errorResponse.setError(errorDTO);
        log.error("Exception Occurred ", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }



}

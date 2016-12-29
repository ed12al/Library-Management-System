package com.gcit.lms.utils;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({MySQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class, NullPointerException.class})
	public ResponseEntity handleBadRequest(Exception e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
	}
	
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(SQLException.class)
    public ResponseEntity handleSQLException(Exception e) {
    	logger.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e){
		logger.warn(e.getMessage());
		return ResponseEntity.status(500).body("Unexpected Error");
	}
}

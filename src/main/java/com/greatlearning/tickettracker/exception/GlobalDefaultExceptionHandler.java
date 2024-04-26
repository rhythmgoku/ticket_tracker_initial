package com.greatlearning.tickettracker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		String errorCode = "404";
		if (exception instanceof HttpServerErrorException) {
			HttpServerErrorException ex = (HttpServerErrorException) exception;
			errorCode = ex.getStatusCode().value() + "";
		}
		modelAndView.addObject("exception", exception.getLocalizedMessage());
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.addObject("errorCode", errorCode);

		modelAndView.setViewName(DEFAULT_ERROR_VIEW);
		return modelAndView;
	}
}
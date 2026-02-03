package com.bitbitforum.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Order(1)
public class CustomErrorController implements ErrorController {

    // Обработка ошибки 403 - Доступ запрещен
    @GetMapping("/403")
    public String accessDenied() {
        return "errorpages/403";
    }

    // Обработка ошибки 404 - Страница не найдена
    @GetMapping("/404")
    public String notFound() {
        return "errorpages/404";
    }

    // Обработка ошибки 500 - Внутренняя ошибка сервера
    @GetMapping("/500")
    public String internalServerError() {
        return "errorpages/500";
    }

    // Общий обработчик всех ошибок
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorpages/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorpages/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errorpages/403";
            }
        }
        
        // Страница по умолчанию для неизвестных ошибок
        return "errorpages/500";
    }

    // Обработка исключений в контроллерах
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("errorpages/500");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}


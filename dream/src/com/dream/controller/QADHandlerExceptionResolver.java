package com.dream.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class QADHandlerExceptionResolver implements HandlerExceptionResolver{
    private static Logger logger = LoggerFactory.getLogger(QADHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.error("Catch Exception: ",ex);//把漏网的异常信息记入日志
        Map<String,Object> map = new HashMap<String,Object>();
        StringPrintWriter strintPrintWriter = new StringPrintWriter();
        ex.printStackTrace(strintPrintWriter);
//        map.put("errorMsg", strintPrintWriter.getString());//将错误信息传递给view
        map.put("errorMsg", ex.getMessage());//将错误信息传递给view
        return new ModelAndView("error",map);
    }

}


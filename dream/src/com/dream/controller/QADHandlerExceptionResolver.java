package com.dream.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dream.model.MsgResult;
import com.dream.service.util.AjaxStatusCodeType;
import com.dream.service.util.HtmlUtil;
import com.dream.service.util.RequestUtil;

public class QADHandlerExceptionResolver implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory
			.getLogger(QADHandlerExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String msg = null; // 错误信息
		Integer code = null; // 错误状态码
		if (ex instanceof Exception) {
			msg = ex.getMessage();
		} else if (ex instanceof NullPointerException) {
			msg = "空指针异常!";
		} else if (ex instanceof IOException) {
			msg = "文件读写异常!";
		}
		if (StringUtils.isBlank(msg)) {
			msg = "系统异常!";
		}
		logger.error( ex.getLocalizedMessage(),ex);
		return handlerError(request, response, code, msg); // 处理
	}

	public ModelAndView handlerError(HttpServletRequest request,
			HttpServletResponse response, Integer code, String msg) {
		if (response.isCommitted()) {
			return null;
		}
		response.setCharacterEncoding("UTF-8");
//		if (RequestUtil.isAjax(request)) {
			response.setStatus(200);
			response.setContentType("application/json; charset=UTF-8");
			if (code == null) {
				code = AjaxStatusCodeType.ERROR.code;
			}
			MsgResult res = new MsgResult();
			res.setErrorMsg(msg);
			HtmlUtil.writerJson(response, res);
			return null;
//		} else {
//			response.setStatus(200);
//			response.setContentType("text/html; charset=UTF-8");
//
//			Map<String, Object> context = new HashMap<String, Object>();
//			context.put("errorMsg", msg);
//			return new ModelAndView("error", context);
//		}
	}

}

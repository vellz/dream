package com.dream.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.dream.model.User;
import com.dream.service.util.AjaxStatusCodeType;
import com.dream.service.util.HtmlUtil;

public class BaseController {
	public final static String SUCCESS = "success";

	public final static String MSG = "msg";

	public final static String DATA = "data";

	public final static String CODE = "statusCode";

	public final static String LOGOUT_FLAG = "logoutFlag";
	
	public final static String SESSION_USER="SESSION_USER";
	
	/**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,User user){
		 user.setPassword(null);
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static User getUser(HttpServletRequest request){
		return (User)request.getSession(true).getAttribute(SESSION_USER);
	 }
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// 添加url到 Map中
		// rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}

	public ModelAndView forword(String viewName, Map<String, Object> context) {
		return new ModelAndView(viewName, context);
	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		result.put(CODE, AjaxStatusCodeType.SUCCESS.code);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		result.put(CODE, AjaxStatusCodeType.ERROR.code);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * @param response
	 * @param statusCode
	 * @param message
	 */
	public void sendMessage(HttpServletResponse response, AjaxStatusCodeType statusCode, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		result.put(CODE, statusCode.code);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * @param response
	 * @param statusCode
	 */
	public void sendMessage(HttpServletResponse response, AjaxStatusCodeType statusCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, statusCode.name);
		result.put(CODE, statusCode.code);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * @param response
	 * @param result
	 */
	public void sendMessage(HttpServletResponse response, Map<String, Object> result) {
		if(!result.containsKey(CODE)){
			result.put(CODE, AjaxStatusCodeType.SUCCESS.code);
			
		}
		if(!result.containsKey(SUCCESS)){
			result.put(SUCCESS, true);
		}
		HtmlUtil.writerJson(response, result);
	}

	
//	/********************* 获取访问参数 *******************/
//	/*******************
//	 * 操作Cookie
//	 * 
//	 * @throws Exception
//	 ********************/
//	protected void savaLoginInfo(SysUser user, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String key = AutoLoginKeyUtil.getLKeyPassWordIsMD5(user.getEmail(), user.getPassword());
//		Cookie cookie = new Cookie("LKEY", key);//默认关闭浏览器清空
////		cookie.setMaxAge(15 * 24 * 3600);// 15天
//		cookie.setPath("/");
//		response.addCookie(cookie);
//		user.setPassword(null);
//		SessionUtils.setUser(request, user);
//	}
}

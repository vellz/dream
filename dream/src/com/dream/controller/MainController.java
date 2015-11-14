package com.dream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dream.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/get.do")
	public String getAllUser(HttpServletRequest request){
		return "allUser";
	}
	
//	/**
//	 * 添加用户并重定向
//	 * @param user
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/addUser")
//	public String addUser(User user,HttpServletRequest request){
//		userService.save(user);
//		return "redirect:/user/getAllUser";
//	}
//	
//	/**
//	 *编辑用户
//	 * @param user
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/updateUser")
//	public String updateUser(User user,HttpServletRequest request){
//		
//		
//		if(userService.update(user)){
//			user = userService.findById(user.getId());
//			request.setAttribute("user", user);
//			return "redirect:/user/getAllUser";
//		}else{
//			return "/error";
//		}
//	}
//	/**
//	 * 根据id查询单个用户
//	 * @param id
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getUser")
//	public String getUser(int id,HttpServletRequest request){
//		
//		request.setAttribute("user", userService.findById(id));
//		return "/editUser";
//	}
//	/**
//	 * 删除用户
//	 * @param id
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping("/delUser")
//	public void delUser(int id,HttpServletRequest request,HttpServletResponse response){
//		String result = "{\"result\":\"error\"}";
//		
//		if(userService.delete(id)){
//			result = "{\"result\":\"success\"}";
//		}
//		
//		response.setContentType("application/json");
//		
//		try {
//			PrintWriter out = response.getWriter();
//			out.write(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
}

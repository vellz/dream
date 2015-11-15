package com.dream.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dream.model.MsgResult;
import com.dream.model.User;
import com.dream.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private final static String CHARSET = ";charset=UTF-8";
	@Autowired
	private UserService userService;
	
	
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public ModelAndView getAllUser(HttpServletRequest request)throws Exception{
		List<User> findAll = userService.findAll();
		request.setAttribute("userList", findAll);
		Map<String,Object>  context = getRootMap();
		return forword("allUser", context);
	}
	
	/**
	 * reg
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/reg",produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	@ResponseBody
	public MsgResult reg(User user,HttpServletRequest request) throws Exception{
		MsgResult msgResult = userService.add(user);
		return msgResult;
	}
	
	@RequestMapping("/get")
	public @ResponseBody String get() {   
		return "测试用户";
    }
	
	/**
	 * reg
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public MsgResult getUser(User userReq,HttpServletRequest request)throws Exception{
		User dbuser=getUser(request);
		if(userReq.getId()!=null){
			dbuser = userService.getUserMapper().selectByPrimaryKey(userReq.getId());
		}
		MsgResult msgResult = new MsgResult();
		msgResult.setData(dbuser);
		return msgResult;
	}
	
	/**
	 * reg
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	@ResponseBody
	public MsgResult login(User userReq,HttpServletRequest request) throws Exception{
		MsgResult msgResult = new MsgResult();
		msgResult = userService.getUser(userReq);
		if(msgResult.getSuccess()!=null&&msgResult.getSuccess()){
			setUser(request, (User)msgResult.getData());
		}
		return msgResult;
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request) throws Exception{
		return "/addUser";
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

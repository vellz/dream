package com.dream.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.mapper.UserOrderMapper;
import com.dream.model.MsgResult;
import com.dream.model.User;
import com.dream.model.UserOrder;
import com.dream.model.UserOrderExample;
import com.dream.service.UserService;

@Controller
@RequestMapping("/")
public class MainController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserOrderMapper orderMapper;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/orders")
	@ResponseBody
	public List<UserOrder> orders(User user,HttpServletRequest request){
		Long userId = user.getId();
		UserOrderExample example = getOrders(userId);
		List<UserOrder> list = orderMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	public MsgResult orders(UserOrder userOrder,HttpServletRequest request){
		MsgResult mr=new MsgResult();
		userOrder.setCreateTime(new Date());
		userOrder.setUserId(1l);
		int i=orderMapper.insertSelective(userOrder);
		if(i>0){
			mr.setSuccessMsg("success");
		}else{
			mr.setErrorMsg("fail");
		}
		return mr;
	}
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/myOrders")
	@ResponseBody
	public List<UserOrder> getAllUser(HttpServletRequest request){
		Long userId = getUser(request).getId();
		UserOrderExample example = getOrders(userId);
		List<UserOrder> list = orderMapper.selectByExample(example);
		return list;
	}

	private UserOrderExample getOrders(Long userId) {
		UserOrderExample example=new UserOrderExample();
		if(userId!=null){
			example.createCriteria().andUserIdEqualTo(userId);
		}
		return example;
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

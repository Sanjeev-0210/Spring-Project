package org.jsp.usermvcapp.controller;

import org.jsp.usermvcapp.dao.UserDao;
import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/open-register")
	public ModelAndView openRegister(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@ResponseBody
//	@RequestMapping(value="/register",method=RequestMethod.POST)
	@PostMapping(value="/register")//here value is optional
	public String saveUser(@ModelAttribute(name="user")User user) {
		user=userDao.saveUser(user);
		return "User Saved with Id:"+user.getId();
	}
	
	@GetMapping("/open-view")
	public String openView(@RequestParam String view) {
		return view;
	}
	
	@GetMapping(value="/find-by-id")
	public ModelAndView findById(@RequestParam(name="id")int id,ModelAndView modelAndView) {
		User user=userDao.findById(id);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Invalid user Id!!!");
		return modelAndView; 
	}
	
	@RequestMapping(value="/open-update")
	public ModelAndView openUpdate(ModelAndView modelAndView) {
		modelAndView.setViewName("updateUser");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping(value="/updateUser")
	public String updateUser(@ModelAttribute(name="user") User user) {
		user=userDao.updateUser(user);
		return "User Updated with Id:"+user.getId();
		
	}
	
	@GetMapping(value="/deleteUser")
	public ModelAndView deleteUser(@RequestParam(name="id") int id,ModelAndView modelAndView) {
		 modelAndView.setViewName("delete");
		modelAndView.addObject("message", userDao.deleteUser(id)) ;
		return modelAndView;
	}
	
	@GetMapping(value="/verify-Id-Password")
	public ModelAndView verifyUserByIdAndPassword(@RequestParam(name="id")int id,@RequestParam(name="password") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUserByIdandPassword(id, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Invalid user Id or password!!!");
		return modelAndView; 
	}
	
	@GetMapping(value="/verify-phone-Password")
	public ModelAndView verifyUserByPhoneAndPassword(@RequestParam(name="phone")long phone,@RequestParam(name="password") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUserByPhoneandPassword(phone, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Invalid user Id or password!!!");
		return modelAndView; 
	}
	
	@GetMapping(value="/verify-email-Password")
	public ModelAndView verifyUserByEmailAndPassword(@RequestParam(name="email")String email,@RequestParam(name="password") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUserByEmailandPassword(email, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Invalid user Id or password!!!");
		return modelAndView; 
	}
}

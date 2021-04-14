package com.schoolms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.schoolms.common.ReturnMsg;
import com.schoolms.dao.TUsersMapper;
import com.schoolms.model.TUsers;

import com.schoolms.model.PageBean;
import com.schoolms.service.ITUsersService;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hp
 * @since 2020-05-01
 */
@ResponseBody
@CrossOrigin
@Controller
@RequestMapping("/tusers")
public class TUsersController {
	@Autowired
	
	private ITUsersService usersService;
	//private IAdminService aadminService;
	Map<String, Object> map = new HashMap<String, Object>();
	//因为这个简单的东西浪费了7个小时
	@ResponseBody
	@RequestMapping(value="/getusers",produces = {"text/plain;charset=UTF-8"})
	@CrossOrigin
	public String  getUsers(@RequestBody TUsers user) {
		Wrapper<TUsers> wrapper = new EntityWrapper<>();
		if(user!=null && !StringUtils.isNullOrEmpty(user.getNumber())) {
			wrapper.eq("number", user.getNumber());
		}
		
		//List<TUsers> list = usersService.selectList(wrapper);
		//TUsers tt=(TUsers) usersService.selectById(user.getNumber());
		String  list1= usersService.getzhiwu(user.getNumber(),user.getPassword());

		System.out.println(list1);
		return list1;
	}
	
	@ResponseBody
	@RequestMapping("/getusersm")
	@CrossOrigin
	public List<TUsers>  getUsersM(@RequestBody TUsers user) {
		Wrapper<TUsers> wrapper = new EntityWrapper<>();
		if(user!=null && !StringUtils.isNullOrEmpty(user.getNumber())) {
			wrapper.eq("number", user.getNumber());
		}
		
		List<TUsers> list = usersService.selectList(wrapper);
		//TUsers tt=(TUsers) usersService.selectById(user.getNumber());
		//String  list1= usersService.getzhiwu(user.getNumber(),user.getPassword());

		//System.out.println(list1);
		return list;
	}
	@ResponseBody
	@RequestMapping("/getusersj")
	@CrossOrigin
	public List<TUsers>  getUsersj(@RequestBody TUsers user) {
		Wrapper<TUsers> wrapper = new EntityWrapper<>();
		if(user!=null && !StringUtils.isNullOrEmpty(user.getZhiwu())) {
			wrapper.eq("zhiwu", user.getZhiwu());
		}
		
		List<TUsers> list = usersService.selectList(wrapper);
		//TUsers tt=(TUsers) usersService.selectById(user.getNumber());
		//String  list1= usersService.getzhiwu(user.getNumber(),user.getPassword());

		//System.out.println(list1);
		return list;
	}

	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody TUsers t) {
		ReturnMsg rm = new ReturnMsg();
		boolean b = usersService.insert(t);
		
		if(b) {
			rm.setCode("0");
			rm.setMsg("添加成功");
		} else {
			rm.setCode("1");
			rm.setMsg("添加失败");
		}
		
		return rm;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	@CrossOrigin
	public ReturnMsg delete(@RequestBody TUsers t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<TUsers> wrapper = new EntityWrapper<TUsers>();

		wrapper.eq("number", t.getNumber());

		boolean b = usersService.delete(wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("删除成功");
		} else {
			rm.setCode("1");
			rm.setMsg("删除失败");
		}
	
		return rm;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	@CrossOrigin
	public ReturnMsg update(@RequestBody TUsers t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<TUsers> wrapper = new EntityWrapper<TUsers>();
		wrapper.eq("number", t.getNumber());

		boolean b = usersService.update(t, wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("更新成功");
		} else {
			rm.setCode("1");
			rm.setMsg("更新失败");
		}
		return rm;
	}
	
	   @CrossOrigin
	    @ResponseBody
	    @RequestMapping("/login")
	    public ReturnMsg login(@RequestBody TUsers user){
		System.out.println("number:"+user.getNumber()+",password:"+user.getPassword());
	        boolean flag = usersService.login(user.getNumber(), user.getPassword());
	        ReturnMsg rm = new ReturnMsg();
	        if(flag){
	        	rm.setCode("1");
				rm.setMsg("登录成功！");
	        }else {
	        	rm.setCode("0");
				rm.setMsg("用户名或密码错误！");
	        }
	       return rm;

	    }
}
	/*
	/**
	 * 获得分页对象

	 * @param pageNum   当前是第几页
	 * @param pageSize  当前页一共显示多少条记录
	 * @return
	 */
	/*
 @RequestMapping(value="pgeInfo",method=RequestMethod.GET)
 @ResponseBody
 public PageInfo <TUsers> getPageInfo(int pageNum,int pageSize){
	 //1.通过PageHelper的静态方法开始获取分页数据
	 //指定当前是第几页以及每页显示多少条记录
	 PageHelper.startPage(pageNum, pageSize);
	//3.获得所有商品记录
	 List<TUsers> list = usersService.selectList(null);
	 //获取分页对象
	 PageInfo<TUsers> pageInfo=new PageInfo<TUsers>(list);
	 System.out.println(pageInfo);
	return pageInfo;
	 
 }s
	 
 */
	


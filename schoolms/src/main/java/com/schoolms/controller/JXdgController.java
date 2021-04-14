package com.schoolms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.schoolms.common.ReturnMsg;
import com.schoolms.model.JRiLi;
import com.schoolms.model.JRiZhi;
import com.schoolms.model.JXdg;
import com.schoolms.model.TScore;
import com.schoolms.service.IJXdgService;

import com.schoolms.service.JXdgServicelmpl;

/**
 * <p>
 * 教学日历表 前端控制器
 * </p>
 *
 * @author xzw
 * 
 */
 @ResponseBody
 @Controller
 @RequestMapping("/jxdg")
	@CrossOrigin
public class JXdgController {
   @Autowired
   private IJXdgService jxdgservice;
   private JXdgServicelmpl jxdgservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object service;
	@ResponseBody
	@RequestMapping("/getjxdg")
	@CrossOrigin
	public List<JXdg> getjxdg(@RequestBody JXdg jxdg) {
		Wrapper<JXdg> wrapper = new EntityWrapper<>();
		
		if(jxdg!=null && !StringUtils.isNullOrEmpty(jxdg.getCoursename())) {
			wrapper.eq("coursename", jxdg.getCoursename());
		}
		List<JXdg> list = jxdgservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}//审核用
	@ResponseBody
	@RequestMapping("/getjxdgshenhe")
	@CrossOrigin
	public List<JXdg> getjxdgshenhe(@RequestBody JXdg jxdg) {
		Wrapper<JXdg> wrapper = new EntityWrapper<>();
		
		if(jxdg!=null && !StringUtils.isNullOrEmpty(jxdg.getState())) {
			wrapper.eq("state", jxdg.getState());
		}
		List<JXdg> list = jxdgservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//按上传者查找
	@ResponseBody
	@RequestMapping("/getjxdgss")
	@CrossOrigin
	public List<JXdg> getjxdgss(@RequestBody JXdg jxdgs) {
		System.out.println(jxdgs);
		Wrapper<JXdg> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", jxdgs.getShangchuanzhe());
		//}
		List<JXdg> list = jxdgservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody JXdg t) {
		Wrapper<JXdg> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = jxdgservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody JXdg t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<JXdg> wrapper = new EntityWrapper<JXdg>();

		wrapper.eq("file", t.getFile());

		boolean b = jxdgservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody JXdg t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<JXdg> wrapper = new EntityWrapper<JXdg>();
		wrapper.eq("file", t.getFile());

		boolean b = jxdgservice.update(t, wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("更新成功");
		} else {
			rm.setCode("1");
			rm.setMsg("更新失败");
		}
		return rm;
	}

//
	private Map<String,Object> result=new HashMap<String,Object>();
		@RequestMapping("/pageInfo")
		 @ResponseBody
			@CrossOrigin
		 public Map<String,Object> getPageInfo( int pageNum,int rows){
			 //1.通过PageHelper的静态方法开始获取分页数据
			 //指定当前是第几页以及每页显示多少条记录
			
			System.out.println(pageNum);
			Wrapper<JXdg> wrapper = new EntityWrapper<>();
			 PageHelper.startPage(1,5);
			//3.获得所有成绩记录
			 List<JXdg> list =(List<JXdg>) jxdgservice.selectList(wrapper);
			 //获取分页对象
			 PageInfo<JXdg> pageInfo=new PageInfo<JXdg>(list,5);
			 //从pageInfo对象取出查询结果
			 //总记录数
			 PaginationInterceptor page1 = new PaginationInterceptor();	
			
			 long total =pageInfo.getTotal();
			 //当前页数据列表
			 List<JXdg> jxdglist=pageInfo.getList();
			 System.out.println(pageInfo);
			 result.put("total",total);
			 result.put("rows",jxdglist);
			 System.out.println(jxdglist);
			 System.out.println(total);
			return result;
			 
		}
	   

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 }

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
import com.mysql.jdbc.StringUtils;
import com.schoolms.common.ReturnMsg;
import com.schoolms.model.PingShi;
import com.schoolms.model.JXdg;
import com.schoolms.service.IPScjService;

import com.schoolms.service.PScjServiceImpl;


/**
 * <p>
 * 教学日志表 前端控制器
 * </p>
 *
 * @author xzw
 * 
 */
@ResponseBody
@Controller
 @RequestMapping("/pingshi")
	@CrossOrigin
public class PScjController {
   @Autowired
   private IPScjService pscjservice;
   private PScjServiceImpl pscjservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object pscj;
	@ResponseBody
	@RequestMapping("/getpscj")
	@CrossOrigin
	public List<PingShi> getPingShi(@RequestBody PingShi pscj) {
		Wrapper<PingShi> wrapper = new EntityWrapper<>();
		if(pscj!=null && !StringUtils.isNullOrEmpty(pscj.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", pscj.getShangchuanzhe());
		}
		List<PingShi> list = pscjservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//按上传者查找
	@ResponseBody
	@RequestMapping("/getpscjss")
	@CrossOrigin
	public List<PingShi> getjxdgss(@RequestBody PingShi pscj) {
	
		Wrapper<PingShi> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", pscj.getShangchuanzhe());
		//}
		List<PingShi> list = pscjservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody PingShi t) {
		Wrapper<PingShi> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = pscjservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody PingShi t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<PingShi> wrapper = new EntityWrapper<PingShi>();

		wrapper.eq("file", t.getFile());

		boolean b = pscjservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody PingShi t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<PingShi> wrapper = new EntityWrapper<PingShi>();
		wrapper.eq("coursename", t.getClassname());

		boolean b = pscjservice.update(t, wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("更新成功");
		} else {
			rm.setCode("1");
			rm.setMsg("更新失败");
		}
		return rm;
	}


 }



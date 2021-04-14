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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.schoolms.common.ReturnMsg;
import com.schoolms.model.JRiLi;
import com.schoolms.model.JXdg;
import com.schoolms.service.IJRiLiService;
import com.schoolms.service.JRiLiServicelmpl;

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
 @RequestMapping("/jrili")
public class JRiLiController {
   @Autowired
   private IJRiLiService jriliservice;
   private JRiLiServicelmpl jriliservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object jrili;
   @CrossOrigin
	@ResponseBody
	@RequestMapping("/getjriliall")
	
	public List<JRiLi> getjxdg(@RequestBody JRiLi jrili) {
		Wrapper<JRiLi> wrapper = new EntityWrapper<>();
		
		if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getCoursename())) {
			wrapper.eq("coursename", jrili.getCoursename());
		}
		List<JRiLi> list = jriliservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//审核用
	@ResponseBody
	@RequestMapping("/getjrilishenhe")
	@CrossOrigin
	public List<JRiLi> getjrilishenhe(@RequestBody JRiLi jrili) {
		Wrapper<JRiLi> wrapper = new EntityWrapper<>();
		
		if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getState())) {
			wrapper.eq("state", jrili.getState());
		}
		List<JRiLi> list = jriliservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
   //按上传者查询
	@ResponseBody
	@RequestMapping("/getjrili")
	@CrossOrigin
	public List<JRiLi> getJrili(@RequestBody JRiLi jrili) {
		System.out.println(jrili);
		Wrapper<JRiLi> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", jrili.getShangchuanzhe());
		//}
			System.out.println(wrapper);
			System.out.println(jrili.getShangchuanzhe());
		List<JRiLi> list = jriliservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody JRiLi t) {
		Wrapper<JRiLi> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = jriliservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody JRiLi t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<JRiLi> wrapper = new EntityWrapper<JRiLi>();

		wrapper.eq("file", t.getFile());
		 System.out.println("------------------------------------**---------------------");
		 System.out.println(wrapper);
		 System.out.println("--------------------------------**-------------------------");
		boolean b = jriliservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody JRiLi t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<JRiLi> wrapper = new EntityWrapper<JRiLi>();
		wrapper.eq("file", t.getFile());

		boolean b = jriliservice.update(t, wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("更新成功");
		} else {
			rm.setCode("1");
			rm.setMsg("更新失败");
		}
		return rm;
	}
	private Map<String,Object> result=new HashMap<String,Object>();
	@RequestMapping("/pageInfo")
	 @ResponseBody
		@CrossOrigin
	 public Map<String,Object> getPageInfo( int pageNum,int rows){
		 //1.通过PageHelper的静态方法开始获取分页数据
		 //指定当前是第几页以及每页显示多少条记录
		System.out.println(pageNum);
		Wrapper<JRiLi> wrapper = new EntityWrapper<>();
		 PageHelper.startPage(pageNum, rows);
		//3.获得所有成绩记录
		 List<JRiLi> list =(List<JRiLi>) jriliservice.selectList( wrapper);
		 //获取分页对象
		 PageInfo<JRiLi> pageInfo=new PageInfo<JRiLi>(list);
		 //从pageInfo对象取出查询结果
		 //总记录数
		 long total =pageInfo.getTotal();
		 //当前页数据列表
		 List<JRiLi> jxdglist=pageInfo.getList();
		 System.out.println(pageInfo);
		 result.put("total",total);
		 result.put("rows",jxdglist);
		 System.out.println("---------------------------------------------------------");
		 System.out.println(rows);
		 
		 System.out.println(pageNum);
		return result;
		 
	}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 }

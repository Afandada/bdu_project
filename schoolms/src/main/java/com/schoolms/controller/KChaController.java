
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
import com.schoolms.model.Kaocha;
import com.schoolms.service.IKchaService;
import com.schoolms.service.IKchaServiceImpl;
;

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
 @RequestMapping("/Kaocha")
	@CrossOrigin
public class KChaController{
   @Autowired
   private IKchaService Kaochaservice;
   private IKchaServiceImpl Kaochaservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object service;
	@ResponseBody
	@RequestMapping("/getKaocha")
	@CrossOrigin
	public List<Kaocha> getKaocha(@RequestBody Kaocha Kaocha) {
		Wrapper<Kaocha> wrapper = new EntityWrapper<>();
		
		if(Kaocha!=null && !StringUtils.isNullOrEmpty(Kaocha.getCoursename())) {
			wrapper.eq("coursename", Kaocha.getCoursename());
		}
		List<Kaocha> list = Kaochaservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//按上传者查找
	@ResponseBody
	@RequestMapping("/getkaochass")
	@CrossOrigin
	public List<Kaocha> getKaochass(@RequestBody Kaocha Kaocha) {
		System.out.println(Kaocha);
		Wrapper<Kaocha> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", Kaocha.getShangchuanzhe());
		//}
		List<Kaocha> list = Kaochaservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody Kaocha t) {
		Wrapper<Kaocha> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = Kaochaservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody Kaocha t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<Kaocha> wrapper = new EntityWrapper<Kaocha>();

		wrapper.eq("file", t.getFile());

		boolean b = Kaochaservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody Kaocha t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<Kaocha> wrapper = new EntityWrapper<Kaocha>();
		wrapper.eq("file", t.getFile());

		boolean b = Kaochaservice.update(t, wrapper);

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
			Wrapper<Kaocha> wrapper = new EntityWrapper<>();
			 PageHelper.startPage(1,5);
			//3.获得所有成绩记录
			 List<Kaocha> list =(List<Kaocha>) Kaochaservice.selectList(wrapper);
			 //获取分页对象
			 PageInfo<Kaocha> pageInfo=new PageInfo<Kaocha>(list,5);
			 //从pageInfo对象取出查询结果
			 //总记录数
			 PaginationInterceptor page1 = new PaginationInterceptor();	
			
			 long total =pageInfo.getTotal();
			 //当前页数据列表
			 List<Kaocha> Kaochalist=pageInfo.getList();
			 System.out.println(pageInfo);
			 result.put("total",total);
			 result.put("rows",Kaochalist);
			 System.out.println(Kaochalist);
			 System.out.println(total);
			return result;
			 
		}
	   
 
 }

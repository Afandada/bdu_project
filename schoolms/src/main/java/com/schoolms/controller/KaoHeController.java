
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
import com.schoolms.model.KaoHe;
import com.schoolms.service.IKHeService;
import com.schoolms.service.KaoHeServiceImpl;


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
 @RequestMapping("/kaohe")
	@CrossOrigin
public class KaoHeController {
   @Autowired
   private IKHeService KaoHeservice;
   private KaoHeServiceImpl KaoHeservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object service;
	@ResponseBody
	@RequestMapping("/getkaohe")
	@CrossOrigin
	public List<KaoHe> getKaoHe(@RequestBody KaoHe KaoHe) {
		Wrapper<KaoHe> wrapper = new EntityWrapper<>();
		
		if(KaoHe!=null && !StringUtils.isNullOrEmpty(KaoHe.getCoursename())) {
			wrapper.eq("coursename", KaoHe.getCoursename());
		}
		List<KaoHe> list = KaoHeservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//按上传者查找
	@ResponseBody
	@RequestMapping("/getkaohess")
	@CrossOrigin
	public List<KaoHe> getKaoHess(@RequestBody KaoHe KaoHe) {
		System.out.println(KaoHe);
		Wrapper<KaoHe> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", KaoHe.getShangchuanzhe());
		//}
		List<KaoHe> list = KaoHeservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody KaoHe t) {
		Wrapper<KaoHe> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = KaoHeservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody KaoHe t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<KaoHe> wrapper = new EntityWrapper<KaoHe>();

		wrapper.eq("file", t.getFile());

		boolean b = KaoHeservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody KaoHe t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<KaoHe> wrapper = new EntityWrapper<KaoHe>();
		wrapper.eq("file", t.getFile());

		boolean b = KaoHeservice.update(t, wrapper);

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
			Wrapper<KaoHe> wrapper = new EntityWrapper<>();
			 PageHelper.startPage(1,5);
			//3.获得所有成绩记录
			 List<KaoHe> list =(List<KaoHe>) KaoHeservice.selectList(wrapper);
			 //获取分页对象
			 PageInfo<KaoHe> pageInfo=new PageInfo<KaoHe>(list,5);
			 //从pageInfo对象取出查询结果
			 //总记录数
			 PaginationInterceptor page1 = new PaginationInterceptor();	
			
			 long total =pageInfo.getTotal();
			 //当前页数据列表
			 List<KaoHe> KaoHelist=pageInfo.getList();
			 System.out.println(pageInfo);
			 result.put("total",total);
			 result.put("rows",KaoHelist);
			 System.out.println(KaoHelist);
			 System.out.println(total);
			return result;
			 
		}
	   
 
 }

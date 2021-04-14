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
import com.schoolms.model.JRiZhi;
import com.schoolms.model.JXdg;
import com.schoolms.service.IJRiZhiService;
import com.schoolms.service.JRiZhiServiceImpl;

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
 @RequestMapping("/jxrz")
	@CrossOrigin
public class JRiZhiController {
   @Autowired
   private IJRiZhiService jxrzservice;
   private JRiZhiServiceImpl jxrzservices;
   Map<String, Object> map = new HashMap<String, Object>();

   private Object jxrz;
	@ResponseBody
	@RequestMapping("/getjxrz")
	@CrossOrigin
	public List<JRiZhi> getJrizhi(@RequestBody JRiZhi jxrz) {
		Wrapper<JRiZhi> wrapper = new EntityWrapper<>();
		if(jxrz!=null && !StringUtils.isNullOrEmpty(jxrz.getClassname())) {
			wrapper.eq("classname", jxrz.getClassname());
		}
		List<JRiZhi> list = jxrzservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//审核用
	@ResponseBody
	@RequestMapping("/getjxrzshenhe")
	@CrossOrigin
	public List<JRiZhi> getJrizhishenhe(@RequestBody JRiZhi jxrz) {
		Wrapper<JRiZhi> wrapper = new EntityWrapper<>();
		if(jxrz!=null && !StringUtils.isNullOrEmpty(jxrz.getState())) {
			wrapper.eq("state", jxrz.getState());
		}
		List<JRiZhi> list = jxrzservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	//按上传者查找
	@ResponseBody
	@RequestMapping("/getjxrzss")
	@CrossOrigin
	public List<JRiZhi> getjxdgss(@RequestBody JRiZhi jxrz) {
	
		Wrapper<JRiZhi> wrapper = new EntityWrapper<>();
		//if(jrili!=null && !StringUtils.isNullOrEmpty(jrili.getShangchuanzhe())) {
			wrapper.eq("shangchuanzhe", jxrz.getShangchuanzhe());
		//}
		List<JRiZhi> list = jxrzservice.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody JRiZhi t) {
		Wrapper<JRiZhi> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = jxrzservice.insert(t);
		
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
	public ReturnMsg delete(@RequestBody JRiZhi t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<JRiZhi> wrapper = new EntityWrapper<JRiZhi>();

		wrapper.eq("file", t.getFile());

		boolean b = jxrzservice.delete(wrapper);

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
	public ReturnMsg update(@RequestBody JRiZhi t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<JRiZhi> wrapper = new EntityWrapper<JRiZhi>();
		wrapper.eq("file", t.getFile());

		boolean b = jxrzservice.update(t, wrapper);

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


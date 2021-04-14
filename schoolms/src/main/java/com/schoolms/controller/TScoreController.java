package com.schoolms.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.schoolms.common.ReturnMsg;

import com.schoolms.model.TScore;

import com.schoolms.service.ITScoreService;
import com.schoolms.service.TScoreServiceImpl;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 成绩表 前端控制器
 * </p>
 *
 * @author xzw
 * @since 2020-06-05
 */
@ResponseBody
@Controller
@RequestMapping("/tscore")
public class TScoreController {
	@Autowired
	private ITScoreService scoreService;
	private TScoreServiceImpl scoreServices;
	Map<String, Object> map = new HashMap<String, Object>();
	private Object serivce;
	@ResponseBody
	@RequestMapping("/getscore")
	@CrossOrigin
	public List<TScore> geTScore(@RequestBody TScore score) {
		Wrapper<TScore> wrapper = new EntityWrapper<>();
		if(score!=null && !StringUtils.isNullOrEmpty(score.getnumber())) {
			wrapper.eq("number", score.getnumber());
		}
		List<TScore> list = scoreService.selectList(wrapper);
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	@CrossOrigin
	public ReturnMsg add(@RequestBody TScore t) {
		Wrapper<TScore> wrapper = new EntityWrapper<>();
		ReturnMsg rm = new ReturnMsg();
		boolean b = scoreService.insert(t);
		
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
	public ReturnMsg delete(@RequestBody TScore t) {
		ReturnMsg rm  = new ReturnMsg();
		Wrapper<TScore> wrapper = new EntityWrapper<TScore>();

		wrapper.eq("number", t.getnumber());

		boolean b = scoreService.delete(wrapper);

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
	
	public ReturnMsg update(@RequestBody TScore t){
		ReturnMsg rm  = new ReturnMsg();

		Wrapper<TScore> wrapper = new EntityWrapper<TScore>();
		wrapper.eq("number", t.getnumber());

		boolean b = scoreService.update(t, wrapper);

		if(b) {
			rm.setCode("0");
			rm.setMsg("更新成功");
		} else {
			rm.setCode("1");
			rm.setMsg("更新失败");
		}
		return rm;
	}
	
	//@RequestMapping(value="pageInfo",method=RequestMethod.GET)
	@RequestMapping("/pageInfo")
	 @ResponseBody
	 public PageInfo<TScore> getPageInfo(int pageNum,int pageSize){
		 //1.通过PageHelper的静态方法开始获取分页数据
		 //指定当前是第几页以及每页显示多少条记录
		Wrapper<TScore> wrapper = new EntityWrapper<TScore>();
		 PageHelper.startPage(pageNum, pageSize);
		//3.获得所有成绩记录
		 List<TScore> list = scoreService.selectList(wrapper);
		 //获取分页对象
		 PageInfo<TScore> pageInfo=new PageInfo<TScore>(list);
		 System.out.println(pageInfo);
		return pageInfo;
		 
	}
   
	 @ResponseBody
	    @CrossOrigin
	    @RequestMapping(value="/fileUpload",produces = {"text/plain;charset=UTF-8"})
	    public String handlerForUpload(@RequestParam("fileList") MultipartFile multipartFile, HttpServletRequest request) {
	        //判断所上传文件是否存在
	        if (multipartFile.isEmpty()) {
	            return "上传错误";
	        }
	        //获取上传文件的原始名称
	        String originalFilename = multipartFile.getOriginalFilename();
	        //设置上传文件的保存地址目录
	        String path = request.getSession().getServletContext().getRealPath("").toString()+"/upload/";
	        File desFile = new File(path);
	        //如果保存的地址不存在,就先创建目录
	        if (!desFile.exists()) {
	        	desFile.mkdirs();
	        }
	        //获取当前文件的后缀
	        //String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
	        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
	        //String newFileName = UUID.randomUUID()+ originalFilename+ "." + suffix;
	        try {
	        //创建可能会有错误,强制添加异常
	            multipartFile.transferTo(new File(path + originalFilename));
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "上传错误";
	        }
	        System.out.println(path + originalFilename);
	       // return "上传成功"+"文件地址="+path+ newFileName;   "http://localhost:8888/schoolms/upload/"+;
	        return "http://localhost:8888/schoolms/upload/"+originalFilename;
	    }


}


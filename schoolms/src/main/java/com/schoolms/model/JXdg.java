package com.schoolms.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_jiaoxuedagang")
public class JXdg  implements Serializable{

	/**
	 *教学日志文件表单
	 */
	private static final long serialVersionUID = 1L;
    
	private String coursename;
	private String teacher;
	private String zhuanye;
	private String zqianzi;
	private String yqianzi;
	private String file;
	private String state;
	private String shangchuanzhe;
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getZqianzi() {
		return zqianzi;
	}
	public void setZqianzi(String zqianzi) {
		this.zqianzi = zqianzi;
	}
	public String getYqianzi() {
		return yqianzi;
	}
	public void setYqianzi(String yqianzi) {
		this.yqianzi = yqianzi;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
	    return "JXdg{" +
	    "coursename=" + coursename +
	    ",teacher=" + teacher +
	    ",zhuanye=" + zhuanye +
	    ",zqianzi=" + zqianzi +
	    ",yqianzi="+yqianzi+
	    ",file="+file+
	    ",state="+state+
	    ",shangchuanzhe="+shangchuanzhe+
	    "}";
	}
	public String getShangchuanzhe() {
		return shangchuanzhe;
	}
	public void setShangchuanzhe(String shangchuanzhe) {
		this.shangchuanzhe = shangchuanzhe;
	}
}
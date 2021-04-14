package com.schoolms.model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_jiaoxuerizhi")
public class JRiZhi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private String xueyuan;
	  private String grade;
	  private String zhuanye;
	  private String stunumber;
	  private String classname;
	  private String yqianzi;
	  private String zqianzi;
	  private String xueshi;
	  private String teacher;
	  private String file;
	  private String state;
	  private String shangchuanzhe;
	public String getXueyuan() {
		return xueyuan;
	}
	public void setXueyuan(String xueyuan) {
		this.xueyuan = xueyuan;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getStunumber() {
		return stunumber;
	}
	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getYqianzi() {
		return yqianzi;
	}
	public void setYqianzi(String yqianzi) {
		this.yqianzi = yqianzi;
	}
	public String getZqianzi() {
		return zqianzi;
	}
	public void setZqianzi(String zqianzi) {
		this.zqianzi = zqianzi;
	}
	public String getXueshi() {
		return xueshi;
	}
	public void setXueshi(String xueshi) {
		this.xueshi = xueshi;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
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
	    return "JRiZhi{" +
	    "xueyuan=" + xueyuan +
	    "grade=" + grade +
	     ",zhuanye=" + zhuanye +
	    ",stunumber=" + stunumber +
	    ",classname=" + classname +
	    ",zqianzi=" + zqianzi +
	    ",yqianzi="+yqianzi+
	      ",xueshi=" + xueshi +
	    ",yqianzi="+yqianzi+
	    ",teacher=" + teacher +
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

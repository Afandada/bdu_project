package com.schoolms.model;

import java.io.Serializable;
import java.util.UUID;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_jiaoxuerili")
public class JRiLi  implements Serializable{
 /**
	 * 教学日历文件表
	 */
	private static final long serialVersionUID = 1L;
 private String coursename;
 private String teacher;
 private String zhicheng;
 private String xueyuan;
 private String y_qianzi;
 private String z_qianzi;
 private String classname;
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
public String getZhicheng() {
	return zhicheng;
}
public void setZhicheng(String zhicheng) {
	this.zhicheng = zhicheng;
}
public String getXueyuan() {
	return xueyuan;
}
public void setXueyuan(String xueyuan) {
	this.xueyuan = xueyuan;
}
public String getY_qianzi() {
	return y_qianzi;
}
public void setY_qianzi(String y_qianzi) {
	this.y_qianzi = y_qianzi;
}
public String getZ_qianzi() {
	return z_qianzi;
}
public void setZ_qianzi(String z_qianzi) {
	this.z_qianzi = z_qianzi;
}
public String getClassname() {
	return classname;
}
public void setClassname(String classname) {
	this.classname = classname;
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
public String getShangchuanzhe() {
	return shangchuanzhe;
}
public void setShangchuanzhe(String shangchuanzhe) {
	this.shangchuanzhe = shangchuanzhe;
}
@Override
public String toString() {
    return "JRiLi{" +
    "coursename=" + coursename +
    ",teacher=" + teacher +
    ",zhicheng=" + zhicheng +
    ",xueyuan=" + xueyuan +
    "y_qianzi="+y_qianzi+
    "z_qianzi="+z_qianzi+
    "classname="+classname+
    "file="+file+
    "state="+state+
    "shangchuanzhe="+"shangchuanzhe"+
    "}";
}


}

package com.schoolms.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_kaohezuoye")
public class KaoHe implements Serializable{
private String coursename;
private String classname;
private String pfbiaozhun;
private String zyyaoqiu;
private String file;
private String shangchuanzhe;
public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public String getClassname() {
	return classname;
}
public void setClassname(String classname) {
	this.classname = classname;
}
public String getPfbiaozhun() {
	return pfbiaozhun;
}
public void setPfbiaozhun(String pfbiaozhun) {
	this.pfbiaozhun = pfbiaozhun;
}
public String getZyyaoqiu() {
	return zyyaoqiu;
}
public void setZyyaoqiu(String zyyaoqiu) {
	this.zyyaoqiu = zyyaoqiu;
}
public String getFile() {
	return file;
}
public void setFile(String file) {
	this.file = file;
}
public String getShangchuanzhe() {
	return shangchuanzhe;
}
public void setShangchuanzhe(String shangchuanzhe) {
	this.shangchuanzhe = shangchuanzhe;
}
@Override
public String toString() {
    return "KaoHe{" +
    "coursename=" + coursename +
    ",classname=" + classname +
    ",pfbiaozhun=" + pfbiaozhun+
    ",zyyaoqiu=" + zyyaoqiu+
    ",file="+file+
  
    "shangchuanzhe="+"shangchuanzhe"+
    "}";
}
}

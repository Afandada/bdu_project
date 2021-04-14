package com.schoolms.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_pingshichengji")
public class PingShi implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String coursename;
 private  String classname;
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
    return "PingShi{" +
    "coursename=" + coursename +
    ",classname=" + classname +
    ",file=" + file +
    ",shangchuanzhe=" + shangchuanzhe +
  
    "}";
}
}

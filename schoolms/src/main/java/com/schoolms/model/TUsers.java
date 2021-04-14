package com.schoolms.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hp
 * @since 2020-05-01
 */

@TableName("t_user")
public class TUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    private String number;
    private String username;
    private String password;
    private String department;
    private String zhiwu;
    private String icon;
   public String getNumber() {
   	return number;
   }
   public void setNumber(String number) {
   	this.number = number;
   }
   public String getPassword() {
   	return password;
   }
   public void setPassword(String password) {
   	this.password = password;
   }
   public String getUsername() {
   	return username;
   }
   public void setUsername(String username) {
   	this.username = username;
   }
   public String getZhiwu() {
   	return zhiwu;
   }
   public void setZhiwu(String zhiwu) {
   	this.zhiwu = zhiwu;
   }
   public String getIcon() {
   	return icon;
   }
   public void setIcon(String icon) {
   	this.icon = icon;
   }
   @Override
   public String toString(){
   	return "JDep{"+"number="+number+
   			",username="+username+
   			",password="+password+
   			",zhiwu="+zhiwu+
   			",department="+department+
   			",icon="+icon+"}";
   }
   public String getDepartment() {
   	return department;
   }
   public void setDepartment(String department) {
   	this.department = department;
   }
	

}

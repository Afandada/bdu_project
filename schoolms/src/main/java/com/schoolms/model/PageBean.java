package com.schoolms.model;

import java.io.Serializable;
import java.util.List;
import com.github.pagehelper.Page;
public  class PageBean implements Serializable {
	public static TUsers findusers;
	
    public PageBean(long total2, List<TUsers> result) {
		// TODO Auto-generated constructor stub
	}
	//当前页
    private long total;
    //当前页记录
    private List rows;
}
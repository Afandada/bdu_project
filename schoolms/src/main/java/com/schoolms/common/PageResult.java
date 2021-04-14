package com.schoolms.common;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class PageResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<T> rows;
	
	public PageResult(Long total, List<T> rows){
		this.setTotal(total);
		this.setRows(rows);
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}

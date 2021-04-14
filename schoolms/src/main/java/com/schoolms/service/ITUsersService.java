package com.schoolms.service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.schoolms.model.PageBean;
import com.schoolms.model.TUsers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hp
 * @since 2020-05-01
 */
@Service
public interface ITUsersService extends IService<TUsers> {

	
	boolean login(String number, String password);

	String getzhiwu(String number, String password);
     
	

}

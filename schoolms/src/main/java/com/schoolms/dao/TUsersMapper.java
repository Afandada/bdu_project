package com.schoolms.dao;

import com.schoolms.model.PageBean;
import com.schoolms.model.TUsers;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pagehelper.Page;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hp
 * @since 2020-05-01
 */
public interface TUsersMapper extends BaseMapper<TUsers> {

	TUsers findUserByUserName(String number);

	
	
}

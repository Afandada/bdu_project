package com.schoolms.service;


import com.schoolms.model.PageBean;
import com.schoolms.model.TUsers;

import com.schoolms.dao.TUsersMapper;
import com.schoolms.service.ITUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.apache.xmlbeans.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hp
 * @since 2020-05-01
 */
@Service
public class TUsersServiceImpl extends ServiceImpl<TUsersMapper, TUsers> implements ITUsersService {
	@Autowired
    private TUsersMapper userDao;

    @Override
    public boolean login(String number, String password) {
        TUsers user = userDao.findUserByUserName(number);
        if (user!=null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    @Override
    public String getzhiwu(String number, String password) {
        TUsers user = userDao.findUserByUserName(number);
        
        return user.getZhiwu();
    }
	
		

}

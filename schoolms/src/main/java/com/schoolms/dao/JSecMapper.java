package com.schoolms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.schoolms.model.JSec;

public interface JSecMapper   extends BaseMapper<JSec> {

	JSec findUserByUserName(String number);

}

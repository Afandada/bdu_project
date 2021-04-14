package com.schoolms.dao;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.schoolms.model.PingShi;
public interface PScjMapper extends BaseMapper<PingShi> {

	List<PingShi> selectAll();

	

}
package com.schoolms.service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;
import com.schoolms.model.JSec;
@Service
public interface IJSecService extends IService<JSec> {

	boolean login(String number, String password);

}

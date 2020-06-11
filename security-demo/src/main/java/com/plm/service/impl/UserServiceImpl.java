package com.plm.service.impl;

import com.plm.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String greeting(String name) {
        System.out.println("hello spring security");
        return "";
    }
}

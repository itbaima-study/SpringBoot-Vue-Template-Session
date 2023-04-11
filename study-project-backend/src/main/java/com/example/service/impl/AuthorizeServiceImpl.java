package com.example.service.impl;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null)
            throw new UsernameNotFoundException("用户名不能为空");
        Account account = mapper.findAccountByNameOrEmail(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }

    @Override
    public boolean sendValidateEmail(String email) {
        /**
         * 1. 先生成对应的验证码
         * 2. 把邮箱和对应的验证码直接放到Redis里面（过期时间3分钟，如果此时重新要求发邮件，
         * 那么，只要剩余时间低于2分钟，就可以重新发送一次，重复此流程）
         * 3. 发送验证码到指定邮箱
         * 4. 如果发送失败，把Redis里面的刚刚插入的删除
         * 5. 用户在注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
         */

        return false;
    }
}

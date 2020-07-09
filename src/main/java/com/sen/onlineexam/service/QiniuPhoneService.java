package com.sen.onlineexam.service;

import org.springframework.stereotype.Service;

/**
 * @author sen
 * @date 2020/7/9 0:40
 */
@Service
public interface QiniuPhoneService {
    //发送验证码
    boolean sentCode(String phone);

    //验证验证码
    boolean verifyCode(String phone, String code);
}

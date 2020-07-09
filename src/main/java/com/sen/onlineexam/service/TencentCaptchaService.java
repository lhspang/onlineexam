package com.sen.onlineexam.service;

import org.springframework.stereotype.Service;

/**
 * @author sen
 * @date 2020/7/9 23:07
 */
@Service
public interface TencentCaptchaService {
    boolean captcha(String ticket, String userIp, String randStr);
}

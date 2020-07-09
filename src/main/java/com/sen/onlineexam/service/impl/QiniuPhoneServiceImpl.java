package com.sen.onlineexam.service.impl;

import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import com.sen.onlineexam.service.QiniuPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sen
 * @date 2020/7/9 0:42
 */
public class QiniuPhoneServiceImpl implements QiniuPhoneService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean sentCode(String phone) {
        // 设置需要操作的账号的AK和SK
        final String ACCESS_KEY = "4d2ILVvnJxp_rRf9d6evACyaAIelwT2eca4DVYT8";
        final String SECRET_KEY = "PiFwwD1qDE5Wfs7uJIalrUv5I0jyRUsWiDDTEr9r";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 实例化一个SmsManager对象
        SmsManager smsManager = new SmsManager(auth);
        //获取随机的验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);

        try {
            smsManager.sendMessage("1216651306089848832", new String[]{phone}, map);
            //将验证码存到Redis
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        //从Redis取出验证码
        String realCode = redisTemplate.opsForValue().get(phone);
        if (realCode != null) {
            return realCode.equals(code);
        }
        return false;
    }
}

package com.sen.onlineexam.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public String encryptPassword(String password,String salt){
        return new SimpleHash("MD5",password,salt,3).toString();
    }

    public String generateSalt(){
        return randomNumberGenerator.nextBytes().toHex();
    }
}

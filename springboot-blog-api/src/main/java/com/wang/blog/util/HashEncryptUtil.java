package com.wang.blog.util;



import com.wang.blog.pojo.User;
import com.wang.blog.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

public class HashEncryptUtil {
    @Autowired
    UserService userService;

    public static User encrypet(User user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodePassword);
        return user;
    }
}

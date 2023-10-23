package com.mengxuegu.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPwd {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test1() {
        String mengxuegu = passwordEncoder.encode("mengxuegu");
        System.out.println("加密之后的" + mengxuegu);
    }
}

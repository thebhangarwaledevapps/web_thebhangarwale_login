package com.app.login.datasource.local.dao;

import com.app.login.entity.Otp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OtpDaoTest {

    @Autowired
    OtpDao otpDao;

    @Test
    public void test_exist_token_return_true_or_null() {
        // final Otp otp = otpDao.save(new Otp(123456));
        Boolean result = otpDao.existsByTokenAndOtpCode("212121",232323);
        System.out.println(result);
    }


}
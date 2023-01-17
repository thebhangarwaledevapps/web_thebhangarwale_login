package com.app.login;

import com.app.login.datasource.local.dao.CustomerDao;
import com.app.login.datasource.local.dao.FirebaseTokenDao;
import com.app.login.datasource.local.dao.MobileDao;
import com.app.login.datasource.local.dao.OtpDao;
import com.app.login.entity.Customer;
import com.app.login.entity.FirebaseToken;
import com.app.login.entity.Mobile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TestTables {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    FirebaseTokenDao firebaseTokenDao;

    @Autowired
    MobileDao mobileDao;

    @Autowired
    OtpDao otpDao;

    @Test
    void test(){
        customerDao.save(new Customer(Arrays.asList(new FirebaseToken("2348923489")),new Mobile("8806616913","91",null)));
    }

}

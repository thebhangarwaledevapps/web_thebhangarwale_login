package com.app.login.datasource.local.dao;

import com.app.login.entity.Customer;
import com.app.login.entity.FirebaseToken;
import com.app.login.entity.Mobile;
import com.app.login.entity.Otp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

@SpringBootTest
class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Test
    public void test_save_address() {
        customerDao.save(new Customer(
                Arrays.asList(new FirebaseToken("firebaseToken")),
                new Mobile("8806616913","91",new Otp(111111))
        ));
    }


}
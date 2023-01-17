package com.app.login.datasource.local;

import com.app.login.datasource.local.dao.CustomerDao;
import com.app.login.datasource.local.dao.FirebaseTokenDao;
import com.app.login.datasource.local.dao.MobileDao;
import com.app.login.datasource.local.dao.OtpDao;
import com.app.login.entity.Customer;
import com.app.login.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DatabaseDataSourceImpl implements DatabaseDataSource {

    @Autowired
    private MobileDao mobileDao;

    @Autowired
    private OtpDao otpDao;

    @Autowired
    private FirebaseTokenDao firebaseTokenDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Optional<Mobile> getMobileByMobileNumber(String mobileNumber) {
        return mobileDao.findByMobileNumber(mobileNumber);
    }

    @Override
    public Mobile saveMobile(Mobile mobile) {
        return mobileDao.save(mobile);
    }

    @Override
    public String saveMobileAndFetchToken(Mobile mobile) {
        return mobileDao.save(mobile).getOtp().getToken();
    }

    @Override
    public boolean isTokenAndOtpCodeAvailable(String otpToken, int otpCode) {
        return otpDao.existsByTokenAndOtpCode(otpToken, otpCode);
    }

    @Override
    public Optional<Mobile> getMobileByOtpToken(String otpToken) {
        return mobileDao.findByOtpToken(otpToken);
    }

    @Override
    public Optional<Customer> getCustomerByMobileId(String id) {
        return customerDao.findByMobileId(id);
    }

    @Override
    public boolean isFirebaseTokenAvailable(String firebaseToken) {
        return firebaseTokenDao.existsById(firebaseToken);
    }

    @Override
    public String saveCustomerAndFetchId(Customer customer) {
        return customerDao.save(customer).getCustomerId();
    }

    @Override
    public Boolean isCustomerExist(String customerId) {
        return customerDao.existsById(customerId);
    }

}

package com.app.login.datasource.local;

import com.app.login.entity.Customer;
import com.app.login.entity.Mobile;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface DatabaseDataSource {

    Optional<Mobile> getMobileByMobileNumber(String mobileNumber);

    Mobile saveMobile(Mobile mobile);

    String saveMobileAndFetchToken(Mobile mobile);

    boolean isTokenAndOtpCodeAvailable(String otpToken, int otpCode);

    Optional<Mobile> getMobileByOtpToken(String otpToken);

    Optional<Customer> getCustomerByMobileId(String id);

    boolean isFirebaseTokenAvailable(String firebaseToken);

    String saveCustomerAndFetchId(Customer customer);

    Boolean isCustomerExist(String customerId);
}

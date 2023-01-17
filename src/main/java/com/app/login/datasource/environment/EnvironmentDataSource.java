package com.app.login.datasource.environment;

import org.springframework.stereotype.Service;

@Service
public interface EnvironmentDataSource {
    int getOtp();

    String getSmsApiKey();

    String getSmsSenderId();

    String getSmsChannel();

    String getSmsDcs();

    String getSmsFlashSms();

    String getSmsRoute();

    String getSmsContent();

    String getSmsAuth();

    String getDelay();
}

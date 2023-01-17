package com.app.login.datasource.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
public class EnvironmentDataSourceImpl implements EnvironmentDataSource {

    @Autowired private Environment environment;

    @Override
    public int getOtp() {
        return environment.getProperty("otp.otp-code", Integer.class);
    }

    @Override
    public String getSmsApiKey() {
        return environment.getProperty("sms.api-key");
    }

    @Override
    public String getSmsSenderId() {
        return environment.getProperty("sms.sender-id");
    }

    @Override
    public String getSmsChannel() {
        return environment.getProperty("sms.channel");
    }

    @Override
    public String getSmsDcs() {
        return environment.getProperty("sms.dcs");
    }

    @Override
    public String getSmsFlashSms() {
        return environment.getProperty("sms.flash-sms");
    }

    @Override
    public String getSmsRoute() {
        return environment.getProperty("sms.route");
    }

    @Override
    public String getSmsContent() {
        return environment.getProperty("sms.content");
    }

    @Override
    public String getSmsAuth() {
        return environment.getProperty("sms.auth");
    }

    @Override
    public String getDelay() {
        return environment.getProperty("otp.delay-in-delete");
    }
}

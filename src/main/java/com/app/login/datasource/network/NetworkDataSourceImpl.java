package com.app.login.datasource.network;

import com.app.login.datasource.network.client.SmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class NetworkDataSourceImpl implements NetworkDataSource {

    //available for prod environment only
    @Nullable
    @Autowired
    private SmsClient smsClient;

    @Override
    public void sendOtp(
            String smsApiKey,
            String smsSenderId,
            String smsChannel,
            String smsDcs,
            String smsFlashSms,
            String smsRoute,
            String number,
            String content)
    {
        if (smsClient != null) {
            try {
                smsClient.sendOtpToCustomer(
                        smsApiKey,
                        smsSenderId,
                        smsChannel,
                        smsDcs,
                        smsFlashSms,
                        smsRoute,
                        number,
                        content
                );
            } catch (Exception e) {

            }
        }
    }
}

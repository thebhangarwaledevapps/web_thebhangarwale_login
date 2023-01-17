package com.app.login.datasource.network;

import org.springframework.stereotype.Service;

@Service
public interface NetworkDataSource {
    void sendOtp(
            String smsApiKey,
            String smsSenderId,
            String smsChannel,
            String smsDcs,
            String smsFlashSms,
            String smsRoute,
            String number,
            String content
    );
}

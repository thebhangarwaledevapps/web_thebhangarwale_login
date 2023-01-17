package com.app.login.datasource.network.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://smppsmshub.in/", name = "sms")
@Profile("prod")
public interface SmsClient {

	@GetMapping(value="/api/mt/SendSMS")
	void sendOtpToCustomer(
			@RequestParam("APIKey") String apiKey,
			@RequestParam("senderid") String senderId,
			@RequestParam("channel") String channel,
			@RequestParam("DCS") String dcs,
			@RequestParam("flashsms") String flashSms,
			@RequestParam("route") String route,
			@RequestParam("number") String number,
			@RequestParam("text") String text
	);

}

package com.twilio.phonetree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class PhonetreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonetreeApplication.class, args);
		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
		final CreateTunnel createTunnel = new CreateTunnel.Builder()
				.withAddr(8080)
				.build();
		final Tunnel tunnel = ngrokClient.connect(createTunnel);
	}
}

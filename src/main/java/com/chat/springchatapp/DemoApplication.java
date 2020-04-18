package com.chat.springchatapp;

import java.io.IOException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
	}
}

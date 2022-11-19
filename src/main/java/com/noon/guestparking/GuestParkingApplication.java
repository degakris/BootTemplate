package com.noon.guestparking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GuestParkingApplication {

	public static void main(String[] args) {
		log.info("Spring Application initializing...");
		SpringApplication.run(GuestParkingApplication.class, args);
	}

}

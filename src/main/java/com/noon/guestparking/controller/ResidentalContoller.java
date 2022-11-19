package com.noon.guestparking.controller;

import com.noon.guestparking.models.Booking;
import com.noon.guestparking.response.ResponseDto;
import com.noon.guestparking.service.ResidentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResidentalContoller {
	private final ResidentalService residentalService;

	@GetMapping(path = "api/v1/bookingHistory")
	public ResponseDto<List<Booking>> bookingHistory(@RequestParam Integer flatId) {
		return new ResponseDto<>(residentalService.residentBookings(flatId));
	}
}

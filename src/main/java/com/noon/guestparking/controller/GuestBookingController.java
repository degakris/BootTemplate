package com.noon.guestparking.controller;

import com.noon.guestparking.dto.BookingSlotDto;
import com.noon.guestparking.dto.ExitSlotDto;
import com.noon.guestparking.exceptions.ServiceException;
import com.noon.guestparking.models.Booking;
import com.noon.guestparking.response.ResponseDto;
import com.noon.guestparking.service.GuestBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestBookingController {
	private final GuestBookingService guestBookingService;

	@PostMapping(path = "api/v1/bookSlot")
	public ResponseDto<Integer> bookingSlot(@RequestBody final BookingSlotDto bookingSlotDto) throws ServiceException {
		System.out.println("dega");
		return new ResponseDto<>(
				guestBookingService.bookSlot(bookingSlotDto.getVehicleType(), bookingSlotDto.getFlatNumber()));
	}

	@PostMapping(path = "api/v1/freeSlot")
	public ResponseDto<Booking> freeingSlot(@RequestBody final ExitSlotDto exitSlotDto) {
		return new ResponseDto<>(
				guestBookingService.exitSlot(exitSlotDto.getSlotId(), exitSlotDto.getFlatNumber()));
	}

}

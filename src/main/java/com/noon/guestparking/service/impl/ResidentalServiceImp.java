package com.noon.guestparking.service.impl;

import com.noon.guestparking.models.Booking;
import com.noon.guestparking.repostotries.BookingRepo;
import com.noon.guestparking.service.ResidentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentalServiceImp implements ResidentalService {
	private final BookingRepo bookingRepo;

	@Override
	public List<Booking> residentBookings(final Integer flatNumber) {
		final List<Booking>bookingList=bookingRepo.findByFlatNumberAndExitTimeNotNullOrderByExitTimeDesc(flatNumber);
		return null;
	}
}

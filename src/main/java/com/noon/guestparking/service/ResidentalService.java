package com.noon.guestparking.service;

import com.noon.guestparking.models.Booking;

import java.util.List;

public interface ResidentalService {
	List<Booking> residentBookings(final Integer flatNumber);
}

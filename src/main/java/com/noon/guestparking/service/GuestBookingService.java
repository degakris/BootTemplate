package com.noon.guestparking.service;

import com.noon.guestparking.enums.VehicleType;
import com.noon.guestparking.exceptions.ServiceException;
import com.noon.guestparking.models.Booking;

public interface GuestBookingService {

	Integer bookSlot(final VehicleType vehicleType, final Integer flatNumber) throws ServiceException;

	Booking exitSlot(final Integer slotId, final Integer flatNumber);

}

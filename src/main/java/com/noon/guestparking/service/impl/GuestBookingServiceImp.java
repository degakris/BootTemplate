package com.noon.guestparking.service.impl;

import com.noon.guestparking.enums.AvailStatus;
import com.noon.guestparking.enums.VehicleType;
import com.noon.guestparking.exceptions.InvalidArgumentException;
import com.noon.guestparking.exceptions.ServiceException;
import com.noon.guestparking.models.Booking;
import com.noon.guestparking.models.Resident;
import com.noon.guestparking.models.Slots;
import com.noon.guestparking.repostotries.ResidentRepo;
import com.noon.guestparking.repostotries.BookingRepo;
import com.noon.guestparking.repostotries.SlotRepo;
import com.noon.guestparking.service.GuestBookingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class GuestBookingServiceImp implements GuestBookingService {
	private final BookingRepo bookingRepo;
	private final ResidentRepo residentRepo;
	private final SlotRepo slotRepo;

	@Override
	@Transactional
	public Integer bookSlot(final VehicleType vehicleType, final Integer flatNumber) throws ServiceException {
		Optional<Resident> resident = residentRepo.findById(flatNumber);
		if (resident.isEmpty())
			throw new InvalidArgumentException("invalid flatNumber:" + flatNumber);
		final List<Booking> bookingList = bookingRepo
				.findByFlatNumberAndTypeAndExitTime(flatNumber, vehicleType, null);
		if (VehicleType.BIKE.equals(vehicleType) && CollectionUtils.isNotEmpty(bookingList) && bookingList.size() == 1)
			throw new ServiceException("slots are not available for this flat:" + flatNumber);
		if (VehicleType.CAR.equals(vehicleType) && CollectionUtils.isNotEmpty(bookingList) && bookingList.size() == 2)
			throw new ServiceException("slots are not available for this flat:" + flatNumber);
		final List<Slots> slots = slotRepo.findByVehicleTypeAndStatus(vehicleType, AvailStatus.AVAILABLE);
		if (CollectionUtils.isNotEmpty(slots)) {
			Slots slot = slots.stream().findFirst().get();
			final Integer slotId = slot.getSlotId();
			final Booking booking = createBookingObject(flatNumber, vehicleType, slotId);
			bookingRepo.save(booking);
			slot.setStatus(AvailStatus.BOOkED);
			slotRepo.save(slot);
			return slotId;
		}
		throw new ServiceException("slots are not available for this flat:" + flatNumber);
	}

	private Booking createBookingObject(final Integer flatNumber, final VehicleType vehicleType, final Integer slotId) {
		return Booking.builder().flatNumber(flatNumber).entryTime(new Date()).type(vehicleType).slotId(slotId).build();
	}

	@Override
	@Transactional
	public Booking exitSlot(final Integer slotId, final Integer flatNumber) {
		Booking booking = bookingRepo.findBySlotIdAndFlatNumberAndExitTime(slotId, flatNumber, null);
		final Date exitTime = new Date();
		int cost = CalculateCost(booking.getEntryTime(), exitTime, booking.getType());
		Slots slots = slotRepo.getById(slotId);
		slots.setStatus(AvailStatus.AVAILABLE);
		slotRepo.save(slots);
		booking.setCost(cost);
		booking.setExitTime(exitTime);
		bookingRepo.save(booking);
		return booking;
	}

	int CalculateCost(final Date entryTime, final Date exitTime, final VehicleType vehicleType) {
		long secs = (exitTime.getTime() - entryTime.getTime()) / 1000;
		int hours = (int) (secs / 3600);
		int cost = VehicleType.CAR.equals(vehicleType) ? 20 : 10;
		hours = hours - 12;
		if (hours > 0)
			cost = VehicleType.CAR.equals(vehicleType) ? (hours / 12) & 20 : (hours / 12) * 10;
		return cost;
	}
}


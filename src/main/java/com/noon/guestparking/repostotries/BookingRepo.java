package com.noon.guestparking.repostotries;

import com.noon.guestparking.enums.VehicleType;
import com.noon.guestparking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
	List<Booking> findByFlatNumberAndTypeAndExitTime(final Integer flatNumber, final VehicleType vehicleType,
			final Date existTime);

	Booking findBySlotIdAndFlatNumberAndExitTime(Integer slotId, Integer flatNumber, Date o);

	List<Booking> findByFlatNumberAndExitTimeNotNullOrderByExitTimeDesc(final Integer flatNumber);
}

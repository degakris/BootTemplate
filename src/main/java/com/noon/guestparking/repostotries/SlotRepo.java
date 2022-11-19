package com.noon.guestparking.repostotries;

import com.noon.guestparking.enums.AvailStatus;
import com.noon.guestparking.enums.VehicleType;
import com.noon.guestparking.models.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepo extends JpaRepository<Slots,Integer> {

	List<Slots> findByVehicleTypeAndStatus(VehicleType vehicleType, AvailStatus available);
}

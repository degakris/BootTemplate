package com.noon.guestparking.models;

import com.noon.guestparking.enums.VehicleType;
import com.noon.guestparking.models.basemodels.AuditDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@SuperBuilder
public class Booking extends AuditDate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	private	Integer flatNumber;
	private Integer slotId;
	private	Date entryTime;
	private	Date exitTime;
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	private Integer cost;
}

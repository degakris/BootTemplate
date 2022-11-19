package com.noon.guestparking.models;

import com.noon.guestparking.models.basemodels.AuditDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@SuperBuilder
public class Resident extends AuditDate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flatNumber;
	private String name;
}

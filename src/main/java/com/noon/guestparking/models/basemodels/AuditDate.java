package com.noon.guestparking.models.basemodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "updatedTime", typeClass = org.jadira.usertype.dateandtime.joda.PersistentDateTime.class, parameters = {
		@Parameter(value = "Asia/Kolkata", name = "databaseZone"),
		@Parameter(value = "Asia/Kolkata", name = "javaZone") })
public class AuditDate implements Serializable {
	@Type(type = "updatedTime")
	DateTime createdAt;
	@Type(type = "updatedTime")
	DateTime updatedAt;

	@PrePersist
	public void onPersist() {
		this.setCreatedAt(new DateTime());
		onUpdate();
	}

	@PreUpdate
	public void onUpdate() {
		this.setUpdatedAt(new DateTime());
	}

}
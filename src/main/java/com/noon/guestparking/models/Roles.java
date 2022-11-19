package com.noon.guestparking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
	@Id
	private String rid;
	private String roleName;
	private String permissions;
	private String description;
	private Integer status;
}

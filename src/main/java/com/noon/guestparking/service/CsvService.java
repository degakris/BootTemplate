package com.noon.guestparking.service;

import com.noon.guestparking.models.Roles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvService {
	void save(final MultipartFile file);

	List<Roles> getAllRoles();
}

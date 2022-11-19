package com.noon.guestparking.service.impl;

import com.noon.guestparking.models.Roles;
import com.noon.guestparking.repostotries.RoleRepo;
import com.noon.guestparking.service.CsvService;
import com.noon.guestparking.utils.CSVHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvServiceImp implements CsvService {
	private final RoleRepo repository;

	@Override
	public void save(final MultipartFile file) {
		try {
			final List<Roles> rolesList = CSVHelper.csvToRoles(file.getInputStream());
			repository.saveAll(rolesList);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	@Override
	public List<Roles> getAllRoles() {
		return repository.findAll();
	}
}

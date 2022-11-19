package com.noon.guestparking.controller;

import com.noon.guestparking.models.Roles;
import com.noon.guestparking.repostotries.RoleRepo;
import com.noon.guestparking.response.ResponseDto;
import com.noon.guestparking.service.CsvService;
import com.noon.guestparking.utils.CSVHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoleController {
	private final CsvService csvService;
	private final RoleRepo repo;

	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				csvService.save(file);
			} catch (final Exception exception) {
				throw exception;
			}
		}
	}

	@PostMapping("/upload/singleFile")
	public void uploadFile(@RequestParam("file") Roles roles) {
		repo.save(roles);
	}

	@GetMapping("rolesList")
	public ResponseDto<List<Roles>> getAllRolesList() {
		return new ResponseDto<>(csvService.getAllRoles());
	}
}

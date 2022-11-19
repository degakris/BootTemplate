package com.noon.guestparking.utils;

import com.noon.guestparking.models.Roles;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
	public static String TYPE = "text/csv";

	public static boolean hasCSVFormat(final MultipartFile file) {
		return TYPE.equals(file.getContentType());
	}

	public static List<Roles> csvToRoles(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			 CSVParser csvParser = new CSVParser(fileReader,
					 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			final List<Roles> rolesList = new ArrayList<>();
			final Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (final CSVRecord csvRecord : csvRecords) {
				final Roles roles = new Roles();
				roles.setRid(csvRecord.get("Rid"));
				roles.setRoleName(csvRecord.get("RoleName"));
				roles.setPermissions(csvRecord.get("Permissions"));
				roles.setDescription(csvRecord.get("Description"));
				roles.setStatus(Integer.parseInt(csvRecord.get("Status")));
				rolesList.add(roles);
			}

			return rolesList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}


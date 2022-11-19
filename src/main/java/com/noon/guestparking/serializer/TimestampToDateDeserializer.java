package com.noon.guestparking.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

@Slf4j
public class TimestampToDateDeserializer extends JsonDeserializer<DateTime> {
	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
		DateTime date = null;
		String timestamp = p.readValueAs(String.class);
		if (StringUtils.isNotBlank(timestamp)) {
			try {
				date = new DateTime(Long.getLong(timestamp));
			} catch (Exception e) {
				log.error("error in parsing date", e);
				try {
					date = DateTime.parse(timestamp, DateTimeFormat.forPattern("yyyy-MM-dd'T'hh:mm:ss.SSS"));
				} catch (Exception ex) {
					log.error("error in parsing date", ex);
				}
			}
		}
		return date;
	}
}


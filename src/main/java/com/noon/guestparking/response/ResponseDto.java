package com.noon.guestparking.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.noon.guestparking.exceptions.CustomError;
import com.noon.guestparking.serializer.DateToTimeStampSerializer;
import com.noon.guestparking.serializer.TimestampToDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
	private Boolean success;
	private String version = "V1";
	@JsonSerialize(using = DateToTimeStampSerializer.class)
	@JsonDeserialize(using = TimestampToDateDeserializer.class)
	private DateTime date = DateTime.now();
	private ClientMetaData clientMeta;
	private CustomError error;
	T data;

	public ResponseDto(T data) {
		this.success = true;
		this.data = data;
		clientMeta = new ClientMetaData("web_admin", "" + (DateTime.now().getMillis() / 1000));
	}

	public ResponseDto(T data, ClientMetaData clientMeta) {
		this(data);
		this.clientMeta = clientMeta;
	}

	public ResponseDto(Boolean success, T data) {
		this(data);
		this.success = success;
	}

	public ResponseDto(Boolean success, T data, CustomError customError) {
		this(success, data);
		this.error = customError;
	}

}

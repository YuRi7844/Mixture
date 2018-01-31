package com.buskstop.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer : date 타입 yyyy-MM-dd HH:mm:ss로 변환하기 위한 JsonSerializer 구현
 * VO instance변수에 @JsonSerialzer 선언해주자.
 * @author um006
 *
 */
public class DateJsonSerializer extends JsonSerializer<Date>{
	
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		gen.writeString(dateStr);
	}
}

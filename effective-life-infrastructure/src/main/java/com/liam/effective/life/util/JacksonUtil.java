package com.liam.effective.life.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.io.StringWriter;

/**
 * @author liam.li
 * Jackson 工具类
 */
@Slf4j
public class JacksonUtil {

	public static String toJSON(Object object) {
		if (object == null) {
			return null;
		}
		if(object instanceof String) {
			return (String) object;
		}

		try {
			StringWriter writer = new StringWriter();
			JsonGenerator gen = new JsonFactory().createGenerator(writer);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			mapper.writeValue(gen, object);
			return writer.toString();
		} catch (Exception e) {
			log.error("Convert to json error", e);
			throw new RuntimeException(e);
		}
	}

	public static <T extends Serializable> T json2Object(Class<T> clazz, String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException("Json[" + json + "] to object[" + clazz.getSimpleName() + "] error.", e);
		}
	}

	public static <T extends Serializable> T json2Object(String json, TypeReference<T> type) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException("Json[" + json + "] to object[" + type + "] error.", e);
		}
	}

	public static <T> T json2ObjectNormal(String json, TypeReference<T> type) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException("Json[" + json + "] to object[" + type + "] error.", e);
		}
	}

}

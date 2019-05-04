package com.fatec.tcc.commons;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class HoraHandler extends StdDeserializer<Date> {
	private static final long serialVersionUID = 8609197116300033094L;

	public HoraHandler() {
		this(null);
	}

	public HoraHandler(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String date = parser.getText();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}

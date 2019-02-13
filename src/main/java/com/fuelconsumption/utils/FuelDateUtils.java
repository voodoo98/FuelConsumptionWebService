package com.fuelconsumption.utils;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.slf4j.Logger;

import com.fuelconsumption.annotations.Log;

public class FuelDateUtils {

	@Inject
	@Log
	private static Logger LOGGER;

	public static final String FORMAT_DATE_TIME = "YYYY-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "YYYY-MM-dd";
	public static final String[] ACCEPTED_FORMATS = { FORMAT_DATE_TIME, FORMAT_DATE };

	public static Date parse(final String dateString) {
		Date result = null;
		try {
			result = DateUtils.parseDate(dateString, ACCEPTED_FORMATS);
		} catch (final DateParseException e) {
			LOGGER.error("Wrong date format! Accepted formats are " + Arrays.toString(ACCEPTED_FORMATS), e);
		}
		return result;

	}

}

package com.fuelconsumption.providers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fuelconsumption.annotations.Log;

public class LoggerProvider {

    @Produces
    @Log
    private Logger createLogger(final InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }
}

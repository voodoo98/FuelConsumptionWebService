package com.fuelconsumption.webservice;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonStreamingOutput<T> implements StreamingOutput {

    private final ObjectMapper mapper;
    private final T result;

    public JacksonStreamingOutput(final ObjectMapper mapper, final T result) {
        this.mapper = mapper;
        this.result = result;
    }

    @Override
    public void write(final OutputStream out) throws IOException, WebApplicationException {
        this.mapper.writeValue(out, this.result);
    }

}

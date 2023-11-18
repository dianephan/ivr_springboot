package com.twilio.phonetree.ivr;

import com.twilio.twiml.TwiML;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TwiMLMessageConverter extends AbstractHttpMessageConverter<TwiML> {

    public TwiMLMessageConverter() {
        super(MediaType.APPLICATION_XML, MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return TwiML.class.isAssignableFrom(clazz);
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false; // we don't ever read TwiML
    }

    @Override
    protected TwiML readInternal(Class<? extends TwiML> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(TwiML twiML, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(twiML.toXml().getBytes(StandardCharsets.UTF_8));
    }
}

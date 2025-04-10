package com.bqt.flight_booking_server.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.print.attribute.Attribute;

@Converter(autoApply = true)
public class FlightStatusConverter implements AttributeConverter<FlightStatus, String> {

    @Override
    public String convertToDatabaseColumn(FlightStatus attribute) {
        return attribute != null ? attribute.getDescription() : null;
    }

    @Override
    public FlightStatus convertToEntityAttribute(String dbData) {
        return FlightStatus.fromValue(dbData);
    }
}
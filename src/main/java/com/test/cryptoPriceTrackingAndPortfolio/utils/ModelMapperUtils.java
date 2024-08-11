package com.test.cryptoPriceTrackingAndPortfolio.utils;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ModelMapperUtils {

    /**
     * This method copies fields from source to target which isn't null
     *
     * @param source source object
     * @param target target object
     */
    public void copyNonNullFields(Object source, Object target) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(source, target);
    }
}

package com.test.cryptoPriceTrackingAndPortfolio.utils;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ModelMapperUtils {

    public void copyNonNullFields(Object source, Object target) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(source, target);
    }
}

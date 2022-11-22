package com.example.springmvccalcuclator.config.feignconfig;

import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomDivisionDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            String strResponse = new String(response.body().asInputStream().readAllBytes());

            if (response.status() == 400 && strResponse.contains("Division by zero"))
                return new DivisionByZeroException();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Exception("Some error");
    }
}

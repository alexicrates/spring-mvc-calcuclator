package com.example.scheduledtaskmaker.clients;

import com.example.operation.domain.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "calculator", url = "http://localhost:8080/calculator/")
public interface CalculatorClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{type}?a={a}&b={b}", params = {"type", "a", "b"})
    Operation getOperation(@RequestParam("type") Operation.Type type,
                           @RequestParam("a") Double a,
                           @RequestParam("b") Double b);

}

package com.example.springmvccalcuclator.web.feignclients;

import com.example.operation.domain.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${service.name.multiplication}")
public interface MultiplicationServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/MULT?a={a}&b={b}", params = {"a", "b"})
    Operation gerOperation(@RequestParam("a") Double a,
                           @RequestParam("b") Double b);
}

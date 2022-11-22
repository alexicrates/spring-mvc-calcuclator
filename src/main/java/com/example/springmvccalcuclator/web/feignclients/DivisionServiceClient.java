package com.example.springmvccalcuclator.web.feignclients;

import com.example.operation.domain.Operation;
import com.example.springmvccalcuclator.config.feignconfig.DivisionServiceClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${service.name.division}", configuration = DivisionServiceClientConfig.class)
public interface DivisionServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/DIV?a={a}&b={b}", params = {"a", "b"})
    Operation gerOperation(@RequestParam("a") Double a,
                           @RequestParam("b") Double b);
}

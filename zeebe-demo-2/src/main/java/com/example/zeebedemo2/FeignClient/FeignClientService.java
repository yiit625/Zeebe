package com.example.zeebedemo2.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("zebee-demo")
public interface FeignClientService {

    @GetMapping("/resourcemanagement/common")
    String zebeeDemosuYap();


}

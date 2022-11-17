package com.lj.eureka.hello;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private EurekaClient client;

    @GetMapping(value = "/hello")
    public String index(){
        List<InstanceInfo> instanceInfose = client.getInstancesByVipAddress("192.168.2.50", true);
        if(instanceInfose.isEmpty()){
            logger.info("/hello, host: null");

        }else {
            logger.info("/hello, host: " + instanceInfose.get(0).getHostName());
        }
        return "Hello World";
    }


}

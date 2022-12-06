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
        String name = "";
        List<InstanceInfo> instanceInfose = client.getInstancesById("host.docker.internal:hello-service:2222");
        if(instanceInfose.isEmpty()){
            logger.info("/hello, host: null");
        }else {
            name = instanceInfose.get(0).getHostName();
            logger.info("/hello, host: " + instanceInfose.get(0).getHostName());
        }
        return "Hello World" + name;
    }


}

package com.ads.robot.controllers;


import com.ads.robot.entities.Configuration;
import com.ads.robot.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "configuration")
public class    ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;
    @GetMapping("/get")
    public Configuration getFunction(){
        return configurationService.getConfiguration();
    }

    @PutMapping(value = "/update")
    public Configuration updateFunction(@RequestBody Configuration configuration) {
        return configurationService.updateConfiguration(configuration);
    }

}

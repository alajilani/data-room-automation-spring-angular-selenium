package com.ads.robot.services;

import com.ads.robot.entities.Configuration;
import com.ads.robot.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;

    public Configuration getConfiguration(){
        return configurationRepository.findAll().get(0);
    }
    public Configuration updateConfiguration( Configuration configuration){
        return configurationRepository.save(configuration);
    }
}

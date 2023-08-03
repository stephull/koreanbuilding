package com.stephull.projects.koreanbuildingapp.koreanbuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AppPropertyConfig {
    
    @Autowired private Environment env;

    public String getConfigValue(String configKey) {
        return env.getProperty(configKey);
    }

}

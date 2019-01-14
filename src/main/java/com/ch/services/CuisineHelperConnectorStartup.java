package com.ch.services;

import com.ch.license.SmileLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CuisineHelperConnectorStartup implements CommandLineRunner {
    @Autowired
    private CuisineHelperService cuisineHelperService;

    @Override
    public void run(String... args) {
        SmileLicense.addLicense();
        cuisineHelperService.createNetwork();
        cuisineHelperService.getNetwrok().readFile("src/cuisine.xdsl");
    }
}

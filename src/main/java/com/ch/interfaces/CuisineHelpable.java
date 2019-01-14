package com.ch.interfaces;

import smile.Network;

import java.util.List;
import java.util.Map;

public interface CuisineHelpable {
    void createNetwork();
    Network getNetwrok();
    void clearEvidences();
    List<String> getSupportedCuisines(Map<String, String> nodes);
}

package com.ch.services;

import com.ch.interfaces.CuisineHelpable;
import org.springframework.stereotype.Service;
import smile.Network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CuisineHelperService implements CuisineHelpable {
    private Network network;

    @Override
    public void createNetwork() {
        network = new Network();
    }

    @Override
    public Network getNetwrok() {
        return network;
    }

    @Override
    public void clearEvidences() {
        network.clearAllEvidence();
    }

    @Override
    public List<String> getSupportedCuisines(Map<String, String> nodes) {
        clearEvidences();
        nodes.forEach((option, evidence) -> network.setEvidence(option, evidence));
        try {
            network.updateBeliefs();
        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.asList("Nie znaleziono kuchni..");
        }
        return getCuisines(network.getNodeValue("Kuchnia"));
    }

    private List<String> getCuisines(double[] cuisines) {
        List<String> cuisineOutcomes = Arrays.stream(network.getOutcomeIds("Kuchnia"))
                .collect(Collectors.toList());
        List<String> supportedCuisines = new ArrayList<>();
        IntStream.range(0, cuisineOutcomes.size()).forEach(i -> {
            if (cuisines[i] > 0.0) {
                supportedCuisines.add(cuisineOutcomes.get(i));
            }
        });
        return supportedCuisines;
    }
}

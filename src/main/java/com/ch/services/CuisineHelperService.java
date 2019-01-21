package com.ch.services;

import com.ch.interfaces.CuisineHelpable;
import org.springframework.stereotype.Service;
import smile.Network;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
        Map<Double, List<String>> cuisinesByValue = prepareCusinesByValue(cuisines, cuisineOutcomes);
        List<Double> cuisinesValuesList = sortCuisinesValues(cuisines);
        return createSupportedCuisinesList(cuisinesValuesList, cuisinesByValue);
    }

    private Map<Double, List<String>> prepareCusinesByValue(double[] cuisines, List<String> outcomes) {
        Map<Double, List<String>> map = new HashMap<>();
        IntStream.range(0, outcomes.size()).forEach(i -> {
            if (map.containsKey(cuisines[i])) {
                map.get(cuisines[i]).add(outcomes.get(i));
            } else {
                List<String> cuisineNames = new ArrayList<>();
                cuisineNames.add(outcomes.get(i));
                map.put(cuisines[i], cuisineNames);
            }
        });
        return map;
    }

    private List<Double> sortCuisinesValues(double[] cuisines) {
        List<Double> cuisinesValuesList = new ArrayList<>(cuisines.length);
        IntStream.range(0, cuisines.length).forEach(i -> cuisinesValuesList.add(cuisines[i]));
        cuisinesValuesList.sort(Collections.reverseOrder());
        return cuisinesValuesList.stream().distinct().collect(Collectors.toList());
    }

    private List<String> createSupportedCuisinesList(List<Double> cuisinesValuesList,
                                                     Map<Double, List<String>> cuisinesByValue) {
        List<String> supportedCuisines = new ArrayList<>();
        cuisinesValuesList.forEach(value -> {
            if (value > 0.15) {
                BigDecimal cuisineValue = BigDecimal.valueOf(value*100).setScale(2, RoundingMode.HALF_UP);
                cuisinesByValue.get(value).forEach(cuisine -> {
                    String cuisineDisplay = cuisine + "    :   " + cuisineValue + "%";
                    supportedCuisines.add(cuisineDisplay);
                });
            }
        });
        return supportedCuisines;
    }
}

package com.ch.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientNodesService {
    private Map<String, String> nodes = new HashMap<>();

    public Map<String, String> getIngredientNodes() {
        return nodes;
    }

    public void addIngredient(String ingredient) {
        nodes.put(ingredient, "tak");
    }

    public void deleteIngredients(List<String> ingredients) {
        ingredients.forEach(item -> nodes.put(item, "nie"));
    }
}

package com.ch.services;

import com.ch.interfaces.CuisineHelpable;
import org.springframework.stereotype.Service;
import smile.Network;

@Service
public class CuisineHelperService implements CuisineHelpable {
    private Network network;

    public void createNetwork() {
        network = new Network();
    }

    public Network getNetwrok() {
        return network;
    }
}

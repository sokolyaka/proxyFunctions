package com.github.sokolyaka.proxyfunctionsexample.model;

import static java.lang.Thread.sleep;

public class MainInteractor {

    public void loadContentWitDelay() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadContentWithException() {
        throw new RuntimeException("Server is not reachable");
    }
}

package Scenarios;

import Objects.Thing;

import java.util.ArrayList;

public interface Scenario {
    String title = "";
    ArrayList<Thing> objects = new ArrayList<>();

    default String getTitle(){return title; }
    ArrayList<Thing> getObjects();

    ArrayList<Thing> load();
    void reset();
}

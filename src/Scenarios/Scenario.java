package Scenarios;

import Objects.Thing;

import java.util.ArrayList;

public interface Scenario {
    String title = "";
    default String getTitle(){return title; }

    ArrayList<Thing> load();
}

package GUI;

import Application.ApplicationMain;
import Scenarios.ScenarioManager;

import java.util.ArrayList;

public class GUISecretary {
    public static void runSimulation(){
        ApplicationMain.run();
    }

    public static ArrayList<String> getScenarioNames() {
        return ScenarioManager.getScenarioNames();
    }
}

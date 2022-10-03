package GUI;

import Application.ApplicationMain;
import Globals.Settings;
import Objects.Thing;
import Scenarios.ScenarioManager;

import java.util.ArrayList;

public class GUISecretary {
    private static MainMenu mainMenu;
    private static String scenarioToRun = "";

    public static void setMainMenu(MainMenu m){
        mainMenu = m;
    }

    public static void runSimulation(){
        ApplicationMain.run(scenarioToRun);
    }

    public static ArrayList<String> getScenarioNames() {
        return ScenarioManager.getScenarioNames();
    }

    public static void setScenarioToRun(String s){
        scenarioToRun = s;
    }

    public static ArrayList<String> getScenarioObjects() {
        ArrayList<String> objectTitles = new ArrayList<>();
        ArrayList<Thing> objects = ScenarioManager.getScenarioObjects(scenarioToRun);

        for(Thing o : objects){
            objectTitles.add(o.getName() + " " + o.getId());
        }

        return objectTitles;
    }

    public static float getSimTime() {
        return Settings.getInstance().getMaxSimTime();
    }

    public static void setSimTime(float value) {
        Settings.getInstance().setMaxSimTime(value);
    }

    public static float getTimeStep() {
        return Settings.getInstance().getTimeStep();
    }

    public static void setTimeStep(float value) {
        Settings.getInstance().setTimeStep(value);
    }

    public static boolean getSimDimensions() {
        return Settings.getInstance().getSimulationMode();
    }

    public static void setSimDimensions(boolean selected) {
        Settings.getInstance().setSimulationMode(selected ? "3d" : "2d");
    }
}

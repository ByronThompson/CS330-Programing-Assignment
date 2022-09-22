package Application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Globals.*;
import Objects.*;
import Tools.OutputHandler;

public class ApplicationMain {
    static ArrayList<Thing> worldObjects; //Create List for all world objects

    public static void main(String[] args){

        int scenario = 2;

        if(System.console() != null){ // If running from the console, allow user to select a scenario, otherwise just run the default scenario
            scenario = Scenario.chooseScenario();
        }

        worldObjects = Scenario.loadScenario(scenario); // load scenario

        Settings s = Settings.getInstance(); // retrieve settings instance

        while(s.simulate()){ //While simulation is running
            updateLoop(); // Update the world objects
            s.stepTime(); // Increase the simulation time by the timestep
        }

        OutputHandler.getInstance().setFileName("Byron Thompson - CS 330, Dynamic trajectory data.txt");
        OutputHandler.getInstance().writeFile();

        // If running in the console, execute Plotter Program to display output
        if(System.console() != null && new File("Plotter.py").exists()){
            try {
                Runtime.getRuntime().exec("python Plotter.py");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs all necessary updates during the simulation
     */
    private static void updateLoop(){
        for (Thing x: worldObjects) { // For every object in the world
            if(x instanceof Updatable){ // Check if the object is Updatable
                ((Updatable) x).Update(); // If it is, Update the object
            }
        }
    }

    public static Thing findObject(int id){
        for(Thing i : worldObjects){
            if(i.getId() == id){
                return i;
            }
        }

        System.out.println("Object of ID " + id + " not found, returning first object instead");
        return worldObjects.get(0);
    }
}

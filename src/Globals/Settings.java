package Globals;

/**
 * Singleton implementation of Simulation Settings
 * All Fields should be somewhat self-explanatory
 */
public class Settings {
    private static Settings instance;

    private float simTime = 0;
    private float timeStep = 0.5F;
    private float maxSimTime = 100;
    private boolean simulate = true;
    private boolean simulationMode = false; // Whether the simulation is intended for 2D or 3D. FALSE = 2D, TRUE = 3D

    private Settings(){

    }

    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }

        return instance;
    }


    public float getSimTime() {
        return simTime;
    }

    public Settings setSimTime(float simTime) {
        this.simTime = simTime;
        return this;
    }

    /**
     * Increases current simulation time by the timestep
     */
    public void stepTime(){
        simTime += timeStep;
        if(simTime > maxSimTime){ // If simulation time has reached maximum, stop sim
            simulate = false;
        }
    }

    public float getTimeStep() {
        return timeStep;
    }

    public Settings setTimeStep(float timeStep) {
        this.timeStep = timeStep;
        return this;
    }

    public float getMaxSimTime() {
        return maxSimTime;
    }

    public Settings setMaxSimTime(float maxSimTime) {
        this.maxSimTime = maxSimTime;
        return this;
    }

    public boolean simulate(){return simulate;}

    public Settings setSimulationMode(String mode){
        this.simulationMode = mode.equals("3d");

        return this;
    }

    public boolean getSimulationMode(){return this.simulationMode; }

    public Settings resetSimulation(){
        this.simTime = 0;
        this.simulate = true;
        return this;
    }

}

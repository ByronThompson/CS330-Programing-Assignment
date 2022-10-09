package Scenarios;

import Globals.Settings;
import Objects.Creature;
import Objects.Thing;

import java.util.ArrayList;

public class DemoScenario implements Scenario{
    public final static String title = "3D Demo";
    private ArrayList<Thing> objects;

    public DemoScenario(){
        reset();
    }

    @Override
    public ArrayList<Thing> load() {
        return objects;
    }

    @Override
    public void reset() {
        this.objects = new ArrayList<>();

        Creature a = new Creature(2601, new float[]{0, 100, 0}, new float[]{0, -2, 0}, 0, "continue");
        a.setMaxVelocity(5).setMaxAcceleration(5);

        Creature c = new Creature(2603, new float[]{-50, 100, 40}, new float[]{0, 0, 8}, (float) ((3*Math.PI)/4), "seek");
        c.setMaxVelocity(8).setMaxAcceleration(2);
        c.getAi().setTarget(a);

        objects.add(a);
        objects.add(c);
    }

    public String getTitle(){return title; }

    @Override
    public ArrayList<Thing> getObjects() {
        Settings.getInstance().setTimeStep(0.5F).setMaxSimTime(100).setSimulationMode("3d");
        return objects;
    }
}

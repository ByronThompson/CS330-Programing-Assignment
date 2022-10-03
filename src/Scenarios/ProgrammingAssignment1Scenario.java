package Scenarios;

import Globals.Settings;
import Objects.Creature;
import Objects.Thing;

import java.util.ArrayList;

public class ProgrammingAssignment1Scenario implements Scenario{
    public final static String title = "Programming Assignment 1";
    private ArrayList<Thing> objects;

    public ProgrammingAssignment1Scenario(){
        reset();
    }

    @Override
    public ArrayList<Thing> load() {
        return objects;
    }

    @Override
    public void reset() {
        this.objects = new ArrayList<>();

        Creature a = new Creature(2601, new float[]{0, 0, 0}, new float[]{0, 0, 0}, 0, "continue");
        a.setMaxVelocity(0).setMaxAcceleration(0);

        Creature b = new Creature(2602, new float[]{-30, 0, -50}, new float[]{2, 0, 7}, (float) (Math.PI/4), "flee");
        b.setMaxVelocity(8).setMaxAcceleration(1.5F);
        b.getAi().setTarget(a);

        Creature c = new Creature(2603, new float[]{-50, 0, 40}, new float[]{0, 0, 8}, (float) ((3*Math.PI)/4), "seek");
        c.setMaxVelocity(8).setMaxAcceleration(2);
        c.getAi().setTarget(a);

        Creature d = new Creature(2604, new float[]{50, 0, 75}, new float[]{-9, 0, 4}, (float) Math.PI, "arrive");
        d.setMaxVelocity(10).setMaxAcceleration(2);
        d.getAi()
                .setTarget(a)
                .setParameter("arrivalRadius", 4)
                .setParameter("slowingRadius", 32)
                .setParameter("timeToTarget", 1);

        objects.add(a);
        objects.add(b);
        objects.add(c);
        objects.add(d);
    }

    public String getTitle(){return title; }

    @Override
    public ArrayList<Thing> getObjects() {
        Settings.getInstance().setTimeStep(0.5F).setMaxSimTime(50);
        return objects;
    }
}

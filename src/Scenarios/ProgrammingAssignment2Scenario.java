package Scenarios;

import Globals.Settings;
import Objects.Creature;
import Objects.Path;
import Objects.Point;
import Objects.Thing;

import java.util.ArrayList;

public class ProgrammingAssignment2Scenario implements Scenario{
    public final static String title = "Programming Assignment 2";
    private ArrayList<Thing> objects;

    public ProgrammingAssignment2Scenario(){
        reset();
    }

    @Override
    public ArrayList<Thing> load() {
        return objects;
    }

    @Override
    public void reset() {
        this.objects = new ArrayList<>();

        Path p = new Path(0, new Point(1, new float[]{0,0,90})
                , new Point(3, new float[]{-20,0,65})
                , new Point(4, new float[]{20,0,40})
                , new Point(5, new float[]{-40,0,15})
                , new Point(6, new float[]{40,0,-10})
                , new Point(7, new float[]{-60,0,-35})
                , new Point(8, new float[]{60,0,-60})
                , new Point(1, new float[]{0,0,-85}));

        Creature a = new Creature(2701, new float[]{20, 0, 95}, new float[]{0, 0, 0}, 0, "followPath");
        a.setMaxVelocity(4).setMaxAcceleration(2);
        a.getAi().setPath(p).setParameter("pathOffset", 0.04F);

        objects.add(p);
        objects.add(a);


    }

    public String getTitle(){return title; }

    @Override
    public ArrayList<Thing> getObjects() {
        Settings.getInstance().setTimeStep(0.5F).setMaxSimTime(125);
        return objects;
    }


}

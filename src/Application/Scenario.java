package Application;

import Objects.*;
import Globals.*;
import ai.AITools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Scenario {
    /**
     * Generates the world objects for an input scenario
     * @param num The Scenario Number. 0 -> Custom, 1-x -> respective Programming assignment
     * @return ArrayList of objects used in the current scenario
     */
    public static ArrayList<Thing> loadScenario(int num){
        ArrayList<Thing> objects = switch (num) {
            case 0 -> custom();
            case 1 -> PA1();
            case 2 -> PA2();
            default -> custom();
        };

        return objects;
    }

    /**
     * Presents the user with the selection of scenarios to load
     * @return The Scenario Number for the chosen scenario
     */
    public static int chooseScenario(){
        System.out.println("""
                To select a scenario, type the number next to the scenario you wish to load:
                Custom Scenario          -> 0
                Programming Assignment 1 -> 1""");

        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        int selection = 0;

        // Reading data using readLine
        try {
            selection = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return selection;
    }

    /**
     * The Scenario for Programming Assignment 1
     * @return
     */
    private static ArrayList<Thing> PA1(){
        ArrayList<Thing> objects = new ArrayList<>();

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

        Settings.getInstance().setTimeStep(0.5F).setMaxSimTime(50);

        return objects;
    }

    /**
     * The Scenario for Programming Assignment 2
     * @return
     */
    private static ArrayList<Thing> PA2(){
        ArrayList<Thing> objects = new ArrayList<>();

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

        objects.add(a);

        Settings.getInstance().setTimeStep(0.5F).setMaxSimTime(125);

        return objects;
    }

    /**
     * Launches the Custom Scenario creation interface
     * @return User created objects for Custom Scenario
     */
    private static ArrayList<Thing> custom() {
        ArrayList<Thing> objects = new ArrayList<>();

        menu: while(true) {
            System.out.println("""
                    EDIT CUSTOM SCENARIO:
                    Configure Simulation settings -> 0
                    Create Simulation Object      -> 1
                    Run Sim                       -> 2""");

            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            int selection = 2;

            // Reading data using readLine
            try {
                selection = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            switch(selection){
                case 0 -> configureSettings();
                case 1 -> configureObjects(objects);
                case 2 -> {
                    break menu;
                }
                default -> System.out.println("Incorrect input, try again");
            }
            
            
        }
        
        return objects;
    }

    private static void configureObjects(ArrayList<Thing> objects) {
        menu: while(true) {
            System.out.println("""
                    CREATE SIMULATION OBJECTS:
                    Create Character  -> 0
                    Create Point      -> 1
                    Create Path       -> 2
                    Back              -> 3""");

            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            int selection = 2;

            // Reading data using readLine
            try {
                selection = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Thing a = null;

            switch(selection){
                case 0 -> a = createCharacter(objects);
                case 1 -> a = createPoint();
                case 2 -> a = createPath();
                case 3 -> {
                    break menu;
                }
                default -> System.out.println("Incorrect input, try again");
            }

            if(a != null){
                objects.add(a);
            }


        }

    }

    private static void configureSettings() {
        Settings s = Settings.getInstance();

        menu: while(true) {
            System.out.println("""
                    EDIT SIMULATION SETTINGS:
                    Max simulation time  -> 0
                    Simulation Timestep  -> 1
                    Back                 -> 2""");

            int selection = Integer.parseInt(getInput());
            switch(selection){
                case 0:
                    System.out.print("Max simulation time: ");
                    s.setMaxSimTime(Float.parseFloat(getInput()));
                    break;
                case 1:
                    System.out.print("Simulation Timestep: ");
                    s.setTimeStep(Float.parseFloat(getInput()));
                    break;
                case 2:
                    break menu;
                default:
                    System.out.println("Incorrect input, try again");
            }
        }
    }

    private static Point createPoint(int... ar) {
        int id = 0;

        if(ar != null){
             id = ar[0];
        }else{
            System.out.println("ID:");
            id = Integer.parseInt(getInput());
        }


        System.out.println("Initial Position x,y,z :");
        float[] inPos = getVector(getInput());

        return new Point(id, inPos);
    }

    private static Creature createCharacter(ArrayList<Thing> objects) {
        System.out.println("ID:");
        int id = Integer.parseInt(getInput());

        System.out.println("Initial Position x,y,z :");
        float[] inPos = getVector(getInput());

        System.out.println("Initial Velocity x,y,z :");
        float[] inVel = getVector(getInput());

        System.out.println("Initial Orientation ");
        float inOr = Float.parseFloat(getInput());

        System.out.println("AI Model (lowercase):");
        String aiModel = getInput();

        Creature a = new Creature(id, inPos, inVel, inOr, aiModel);

        System.out.println("Max Velocity ");
        a.setMaxVelocity(Float.parseFloat(getInput()));

        System.out.println("Max Acceleration ");
        a.setMaxAcceleration(Float.parseFloat(getInput()));

        AITools.configureAI(a.getAi(), objects);

        return a;
    }

    private static Path createPath(){
        System.out.println("ID:");
        int id = Integer.parseInt(getInput());

        ArrayList<Point> points = new ArrayList<>();

        menu: while (true){
            System.out.println("""
                    EDIT PATH:
                    Add point  -> 0
                    Finish     -> 1""");

            int selection = Integer.parseInt(getInput());
            switch(selection){
                case 0:
                    points.add(createPoint(0));
                    break;
                case 1:
                    break menu;
                default:
                    System.out.println("Incorrect input, try again");
            }

            System.out.println(points.size());
        }

        Path p = new Path(id, points.toArray(new Point[0]));

        System.out.println(p.pathPoints.size());

        return p;
    }

    private static float[] getVector(String in){
        float[] r = {0,0,0};
        String[] s = in.split(",");
        for(int i = 0; i < 3; i++){
            r[i] = Float.parseFloat(s[i]);
        }

        return r;
    }

    private static String getInput(){
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = "";

        // Reading data using readLine
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

}

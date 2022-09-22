package ai;

import Objects.Thing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AITools {

    /**
     * Returns the class of the AI behavior chosen
     * @param model lowercase string name of the chosen AI behavior
     * @return Chosen AI behavior class
     */
    public static Class<? extends AI> getAIModel(String model){
        return switch (model) {
            case "continue" -> AIContinue.class;
            case "flee" -> AIFlee.class;
            case "seek" -> AISeek.class;
            case "arrive" -> AIArrive.class;
            case "followpath" -> AIFollowPath.class;
            default -> AIContinue.class;
        };

    }


    public static void configureAI(AI ai, ArrayList<Thing> objects){
        if (AIFlee.class.equals(ai.getClass()) || AISeek.class.equals(ai.getClass()) || AIFollowPath.class.equals(ai.getClass())) {
            System.out.println("ID of target: ");
            ai.setTarget(findObject(Integer.parseInt(getInput()), objects));
        }else if(AIArrive.class.equals(ai.getClass())){
            System.out.println("ID of target: ");
            ai.setTarget(findObject(Integer.parseInt(getInput()), objects));

            System.out.println("Arrival Radius: ");
            ai.setParameter("arrivalRadius", Float.parseFloat(getInput()));

            System.out.println("Slowing Radius: ");
            ai.setParameter("slowingRadius", Float.parseFloat(getInput()));

            System.out.println("Time to Target: ");
            ai.setParameter("timeToTarget", Float.parseFloat(getInput()));
        }
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

    /**
     * Find object with specified ID in Arraylist
     * @param id The ID of the object to find
     * @param objects ArrayList of Things to search
     * @return Found Thing, or First item in ArrayList if no item found (Probably not the best idea but I don't want to deal with nulls)
     */
    private static Thing findObject(int id, ArrayList<Thing> objects){
        for(Thing i : objects){
            if(i.getId() == id){
                return i;
            }
        }

        return objects.get(0);
    }
}

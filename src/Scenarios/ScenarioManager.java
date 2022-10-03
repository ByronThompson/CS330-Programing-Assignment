package Scenarios;

import Objects.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;
import java.util.stream.Collectors;

public class ScenarioManager {
    private static ArrayList<Scenario> scenarios = new ArrayList<>();


    /**
     * Generates the world objects for an input scenario
     * @param name The Scenario Number. 0 -> Custom, 1-x -> respective Programming assignment
     * @return ArrayList of objects used in the current scenario
     */
    public static ArrayList<Thing> loadScenario(String name){
        return scenarios.stream().filter(scenario -> name.equals(scenario.getTitle())).findFirst().get().load();
    }

    /**
     * Generates list of Scenario instances by instantiating every class in the package which extends the Scenario Interface.
     * This process is done automatically for all Classes and does not require any Hard-coded instantiation.
     * This function is genuine black magic. I am not entirely sure how it works, I only know that it does.
     * If you want to try to figure out how it works, be my guest.
     * There are certainly easier ways to do this, but I didn't want to have any external dependencies and this is the only way I could get it to work in vanilla java.
     * Source: https://stackoverflow.com/questions/1456930/how-do-i-read-all-classes-from-a-java-package-in-the-classpath
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void indexScenarios() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String packageName = "Scenarios";

        packageName = packageName.replace(".", "/");
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(packageName);

        assert packageURL != null;
        if(packageURL.getProtocol().equals("jar")){ // If running as a jar file
            String jarFileName;
            JarFile jf ;
            Enumeration<JarEntry> jarEntries;
            String entryName;

            // build jar file name, then loop through zipped entries
            jarFileName = URLDecoder.decode(packageURL.getFile(), StandardCharsets.UTF_8);
            jarFileName = jarFileName.substring(5,jarFileName.indexOf("!"));
            jf = new JarFile(jarFileName);
            jarEntries = jf.entries();
            while(jarEntries.hasMoreElements()){
                entryName = jarEntries.nextElement().getName();
                if(entryName.startsWith(packageName) && entryName.length()>packageName.length()+5){
                    entryName = entryName.substring(packageName.length(),entryName.lastIndexOf('.'));
                    entryName = entryName.substring(1) + ".class";
                    Class<? extends Scenario> c = getClass(entryName, packageName);
                    if(c != null){
                        scenarios.add(c.getDeclaredConstructor().newInstance());
                    }
                }
            }
        }else{  // if running in IDE

            InputStream stream = ScenarioManager.class.getClassLoader()
                   .getResourceAsStream("Scenarios".replaceAll(" . ", "/"));
            assert stream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Set<Class<? extends Scenario>> a = reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, "Scenarios"))
                    .collect(Collectors.toSet());

            while(a.remove(null));


            for(Class<? extends Scenario> c : a){
                Scenario s = c.getDeclaredConstructor().newInstance();
                scenarios.add(s);
            }
        }
        while(scenarios.remove(null));
    }

    private static Class<? extends Scenario> getClass(String className, String packageName) {
        try {
            Class<?> a = Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
            if(Scenario.class.isAssignableFrom(a) && !a.equals(Scenario.class)){
                return (Class<? extends Scenario>) a;
            }
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public static ArrayList<String> getScenarioNames(){
        ArrayList<String> names = new ArrayList<>();

        for(Scenario c : scenarios){
            names.add(c.getTitle());
        }

        return names;
    }

    public static ArrayList<Thing> getScenarioObjects(String name){
        return scenarios.stream().filter(scenario -> name.equals(scenario.getTitle())).findFirst().get().getObjects();
    }

    public static void resetScenarios(){
        for(Scenario s : scenarios){
            s.reset();
        }
    }
}

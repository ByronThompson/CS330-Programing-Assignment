package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class OutputHandler {
    private static OutputHandler instance;
    private String outputString = "";
    private String fileName = "CS 330, Dynamic trajectory data.txt";

    public static OutputHandler getInstance(){
        if(instance == null){
            instance = new OutputHandler();
        }

        return instance;
    }

    public void addToOutput(String add){
        outputString = outputString + add;
    }

    public void newLine(){
        outputString = outputString + "\n";
    }

    public void setFileName(String newFileName){fileName = newFileName; }

    public void writeFile(){
        System.out.print(outputString);

        // Create new file
        try {
            File output = new File(fileName);
            output.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred creating new file.");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(outputString);
            writer.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred writing to the file.");
            e.printStackTrace();
        }
    }
}

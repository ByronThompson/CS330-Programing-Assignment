package Objects;
import Globals.Settings;
import ai.*;
import Tools.*;

import java.lang.reflect.InvocationTargetException;

// This Class was originally named Character, but I got tired of all the warnings about ambiguity, so now it's called Creature
public class Creature implements Updatable{
    private final int id;

    private float[] position;

    private float[] velocity;
        private float maxVelocity;

    private float[] acceleration = {0,0,0};
        private float maxAcceleration;

    private float orientation;
    private boolean collisionStatus = false;

    private AI ai;

    public Creature(int id, float[] initialPosition, float[] initialVelocity, float initialOrientation, String AIModel){
        this.id = id;
        this.position = initialPosition;
        this.velocity = initialVelocity;
        this.orientation = initialOrientation;
        Class<? extends AI> aimodel = AITools.getAIModel(AIModel);
            try {
                ai = aimodel.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                System.out.println("Unable to create new AI: " + AIModel + " for creature " + id);
                throw new RuntimeException(e);
            }
        ai.setOwner(this);

    }

    @Override
    public float[] getPosition() {
        return this.position;
    }

    @Override
    public Creature setPosition(float[] newPosition) {
        this.position = newPosition;
        return this;
    }

    @Override
    public Creature modifyPosition(float[] deltaP) {
        for(int i = 0; i < this.position.length; i++){
            this.position[i] += deltaP[i];
        }
        return this;
    }

    @Override
    public float[] getVelocity() {
        return this.velocity;
    }

    @Override
    public Creature setVelocity(float[] newVelocity) {
        this.velocity = newVelocity;
        return this;
    }

    @Override
    public Creature modifyVelocity(float[] deltaV) {
        for(int i = 0; i < this.velocity.length; i++){
            this.velocity[i] += deltaV[i];
        }
        return this;
    }

    @Override
    public float getMaxVelocity() {
        return this.maxVelocity;
    }

    @Override
    public Creature setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
        return this;
    }

    @Override
    public Creature modifyMaxVelocity(float deltaMV) {
        this.maxVelocity += deltaMV;
        return this;
    }

    @Override
    public float[] getAcceleration() {
        return this.acceleration;
    }

    @Override
    public Creature setAcceleration(float[] acceleration) {
        this.acceleration = acceleration;
        return this;
    }

    @Override
    public Creature modifyAcceleration(float[] deltaA) {
        for(int i = 0; i < this.acceleration.length; i++){
            this.acceleration[i] += deltaA[i];
        }
        return this;
    }

    @Override
    public float getMaxAcceleration() {
        return this.maxAcceleration;
    }

    @Override
    public Creature setMaxAcceleration(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
        return this;
    }

    @Override
    public Creature modifyMaxAcceleration(float deltaMA) {
        this.maxAcceleration += deltaMA;
        return this;
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public Creature setOrientation(float newOrientation) {
        this.orientation = newOrientation;
        return this;
    }

    @Override
    public Creature modifyOrientation(float deltaO) {
        this.orientation += deltaO;
        return this;
    }

    public AI getAi() {
        return this.ai;
    }

    public int getId(){ return this.id;}

    @Override
    public void Update() {
        printStatus();

        modifyPosition(VectorMath.scalarMultiplyVec3D(velocity, Settings.getInstance().getTimeStep()));
        modifyVelocity(VectorMath.scalarMultiplyVec3D(acceleration, Settings.getInstance().getTimeStep()));

        ai.AISteer();

        if(VectorMath.magnitudeVec3D(velocity) > maxVelocity){
            velocity = VectorMath.normalizeVec3D(velocity);
            velocity = VectorMath.scalarMultiplyVec3D(velocity, maxVelocity);
        }

        if(VectorMath.magnitudeVec3D(acceleration) > maxAcceleration){
            acceleration = VectorMath.normalizeVec3D(acceleration);
            acceleration = VectorMath.scalarMultiplyVec3D(acceleration, maxAcceleration);
        }
    }

    private void printStatus(){
        OutputHandler output = OutputHandler.getInstance();
        output.addToOutput(Settings.getInstance().getSimTime() + ",");
        output.addToOutput(this.id + ",");

        if(Settings.getInstance().getSimulationMode()){
            // 3D Simulation Output

            output.addToOutput(this.position[0] + "," + this.position[1] + "," + this.position[2] + ",");
            output.addToOutput(this.velocity[0] + "," + this.velocity[1] + "," + this.velocity[2] + ",");
            output.addToOutput(this.acceleration[0] + "," + this.acceleration[1] + "," + this.acceleration[2] + ",");
        }else{
            // 2D Simulation Output

            output.addToOutput(this.position[0] + "," + this.position[2] + ",");
            output.addToOutput(this.velocity[0] + "," + this.velocity[2] + ",");
            output.addToOutput(this.acceleration[0] + "," + this.acceleration[2] + ",");
        }

        output.addToOutput(this.orientation + ",");
        output.addToOutput(this.ai.getID() + ",");
        output.addToOutput(this.collisionStatus + "");


        OutputHandler.getInstance().newLine();
    }
}

package Objects;

/**
 * An Updatable Thing is any Thing that has velocity and acceleration, and/or is updated during the update loop of the simulation
 */
public interface Updatable extends Thing{
    float[] velocity = {0, 0, 0};
    float maxVelocity = 0;

    float[] acceleration = {0,0,0};
    float maxAcceleration = 0;

    float[] getVelocity();
    Thing setVelocity(float[] newVelocity);
    Thing modifyVelocity(float[] deltaV);

    float getMaxVelocity();
    Thing setMaxVelocity(float maxVelocity);
    Thing modifyMaxVelocity(float deltaMV);

    float[] getAcceleration();
    Thing setAcceleration(float[] acceleration);
    Thing modifyAcceleration(float[] deltaA);

    float getMaxAcceleration();
    Thing setMaxAcceleration(float maxAcceleration);
    Thing modifyMaxAcceleration(float deltaMA);

    void Update();
}

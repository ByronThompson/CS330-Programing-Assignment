package Objects;

/**
 * A Thing is any object that exists in the "World" of the simulation, Including Characters, Points, Obstacles, and other such objects
 */
public interface Thing {
    String name = "";

    String getName();

    float[] position = {0, 0, 0};

    float orientation = 0;
    int id = 0;

    float[] getPosition();
    Thing setPosition(float[] newPosition);
    Thing modifyPosition(float[] deltaP);

    float getOrientation();
    Thing setOrientation(float newOrientation);
    Thing modifyOrientation(float deltaO);

    int getId();
}

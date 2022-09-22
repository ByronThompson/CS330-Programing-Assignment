package ai;
import Objects.*;

import java.util.ArrayList;

/**
 * Base interface for all movement AI behaviors
 */
public interface AI {
    int id = 0;

    Creature Owner = null;  // The creature the AI controls
    Thing target = null;    // The target for the AI

    int getID();

    AI setOwner(Creature newOwner);
    Creature getOwner();

    AI setTarget(Thing newTarget);
    Thing getTarget();

    default AI addPointToPath(Point p){ return this; }
    default AI addPointToPath(Point p, int index){ return this; }
    default AI setPath(Path path){ return this; }
    default AI newPath(Point[] points){return this; }

    AI setParameter(String parameterName, float parameterValue);    // Interface for allowing manipulation of AI that have non-standard parameters (Such as Arrives Slowing, and arrival radii)

    void AISteer(); // Primary AI update function

}

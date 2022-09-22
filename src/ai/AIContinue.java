package ai;
import Objects.*;

public class AIContinue implements AI{
    private Creature Owner;
    private Thing Target;
    private final int id = 1;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public AIContinue setOwner(Creature c) {
        this.Owner = c;
        return this;
    }

    @Override
    public Creature getOwner() {
        return this.Owner;
    }


    @Override
    public AIContinue setTarget(Thing t) {
        this.Target = t;
        return this;
    }

    @Override
    public Thing getTarget() {
        return this.Target;
    }

    @Override
    public AIContinue setParameter(String parameterName, float parameterValue) {
        return this;
    }

    @Override
    public void AISteer() {

    }

}

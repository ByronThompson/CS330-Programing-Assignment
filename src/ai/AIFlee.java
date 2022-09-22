package ai;

import Objects.*;
import Tools.*;

public class AIFlee implements AI{
    private Creature Owner;
    private Thing Target;
    private final int id = 7;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public AIFlee setOwner(Creature c) {
        this.Owner = c;
        return this;
    }

    @Override
    public Creature getOwner() {
        return this.Owner;
    }


    @Override
    public AIFlee setTarget(Thing t) {
        this.Target = t;
        return this;
    }

    @Override
    public Thing getTarget() {
        return this.Target;
    }

    @Override
    public AIFlee setParameter(String parameterName, float parameterValue) {
        return this;
    }

    @Override
    public void AISteer() {
        float[] linearAcc = VectorMath.subVec3D(this.Owner.getPosition(), this.Target.getPosition());
        linearAcc = VectorMath.normalizeVec3D(linearAcc);
        linearAcc = VectorMath.scalarMultiplyVec3D(linearAcc, this.Owner.getMaxAcceleration());

        this.Owner.setAcceleration(linearAcc);
    }
}

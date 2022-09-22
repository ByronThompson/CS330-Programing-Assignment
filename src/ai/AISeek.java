package ai;

import Objects.*;
import Tools.*;

public class AISeek implements AI{
    private Creature Owner;
    private Thing Target;
    private final int id = 6;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public AISeek setOwner(Creature c) {
        this.Owner = c;
        return this;
    }

    @Override
    public Creature getOwner() {
        return this.Owner;
    }


    @Override
    public AISeek setTarget(Thing t) {
        this.Target = t;
        return this;
    }

    @Override
    public Thing getTarget() {
        return this.Target;
    }

    @Override
    public AISeek setParameter(String parameterName, float parameterValue) {
        return this;
    }

    @Override
    public void AISteer() {
        seek();
    }

    protected void seek(){
        float[] linearAcc = VectorMath.subVec3D(this.Target.getPosition(), this.Owner.getPosition());
        linearAcc = VectorMath.normalizeVec3D(linearAcc);
        linearAcc = VectorMath.scalarMultiplyVec3D(linearAcc, this.Owner.getMaxAcceleration());

        this.Owner.setAcceleration(linearAcc);
    }
}

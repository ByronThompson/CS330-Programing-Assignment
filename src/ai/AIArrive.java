package ai;

import Objects.*;
import Tools.*;

public class AIArrive implements AI{
    private Creature Owner;
    private Thing Target;
    private final int id = 8;

    private float arrivalRadius = 0;
    private float slowingRadius = 0;
    private float timeToTarget = 0;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public AIArrive setOwner(Creature c) {
        this.Owner = c;
        return this;
    }

    @Override
    public Creature getOwner() {
        return this.Owner;
    }


    @Override
    public AIArrive setTarget(Thing t) {
        this.Target = t;
        return this;
    }

    @Override
    public Thing getTarget() {
        return this.Target;
    }

    @Override
    public AIArrive setParameter(String parameterName, float parameterValue) {
        switch (parameterName) {
            case "arrivalRadius" -> this.arrivalRadius = parameterValue;
            case "slowingRadius" -> this.slowingRadius = parameterValue;
            case "timeToTarget" -> this.timeToTarget = parameterValue;
        }

        return this;
    }

    @Override
    public void AISteer() {
        float[] direction = VectorMath.subVec3D(this.Target.getPosition(), this.Owner.getPosition());
        float distance = VectorMath.magnitudeVec3D(direction);
        float speed;

        if(distance < this.arrivalRadius){
            speed = 0;
        }else if(distance > this.slowingRadius){
            speed = this.Owner.getMaxVelocity();
        }else{
            speed = this.Owner.getMaxVelocity() * distance / slowingRadius;
        }

        float[] velocity = VectorMath.scalarMultiplyVec3D(VectorMath.normalizeVec3D(direction), speed);
        float[] result = VectorMath.subVec3D(velocity, this.Owner.getVelocity());
        result = VectorMath.scalarDivideVec3D(result, timeToTarget);

        if(VectorMath.magnitudeVec3D(result) > this.Owner.getMaxVelocity()){
            result = VectorMath.normalizeVec3D(result);
            result = VectorMath.scalarMultiplyVec3D(result, this.Owner.getMaxVelocity());
        }

        this.Owner.setAcceleration(result);
    }
}

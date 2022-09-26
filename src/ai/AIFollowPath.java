package ai;

import Objects.*;

import java.util.ArrayList;

public class AIFollowPath extends AISeek{
    private final int id = 11;

    private float pathOffset = 0;

    private Path path = new Path(999);

    public AIFollowPath(){
        this.setTarget(new Point(0, new float[]{0,0,0}));
    }

    @Override
    public AIFollowPath addPointToPath(Point p){
        this.path.addPoint(p);

        return this;
    }

    @Override
    public AIFollowPath addPointToPath(Point p, int index){
        this.path.addPoint(p, index);

        return this;
    }

    @Override
    public AIFollowPath setPath(Path path){
        this.path = path;

        return this;
    }

    @Override
    public AIFollowPath newPath(int id, Point[] points){
        this.path = new Path(id, points);

        return this;
    }

    @Override
    public void AISteer(){
        followPath();
        seek();
    }

    protected void followPath(){
        float param = path.getParam(getOwner().getPosition());
        float targetParam = Math.min(1, param + pathOffset);
        this.getTarget().setPosition(path.getPathPosition(targetParam));

        // If reach end of path, turn around and follow path back
        if(targetParam == 1){
            path.reversePath();
        }
    }

    @Override
    public AIFollowPath setParameter(String parameterName, float parameterValue) {
        switch (parameterName) {
            case "pathOffset" -> this.pathOffset = parameterValue;
        }

        return this;
    }
}

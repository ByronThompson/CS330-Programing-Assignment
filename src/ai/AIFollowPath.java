package ai;

import Objects.*;

import java.util.ArrayList;

public class AIFollowPath extends AISeek{

    private Path path = new Path();;

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
    public AIFollowPath newPath(Point[] points){
        this.path = new Path(points);

        return this;
    }

    @Override
    public void AISteer(){
        followPath();
        seek();
    }

    protected void followPath(){
        //System.out.println(path.get(0).getPosition()[0]);
    }
}

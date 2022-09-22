package Objects;

import Tools.VectorMath;

import java.util.ArrayList;
import java.util.Arrays;

public class Path {
    private int id = 0;

    private ArrayList<Point> pathPoints = new ArrayList<>();
    private ArrayList<Float> pathParams = new ArrayList<>();
    private ArrayList<Float> lengths = new ArrayList<>();
    private float totalLength = 0;

    public Path(Point... args){
        this.lengths.add(0.0F);
        this.pathPoints.addAll(Arrays.asList(args));
        recalculatePathParams();
    }
    public void addPoint(Point p){
        this.pathPoints.add(p);
        recalculatePathParams();
    }

    public void addPoint(Point p, int index){
        this.pathPoints.add(index, p);
        recalculatePathParams();
    }

    public float getParam(float[] position){
        return 0.0F;
    }

    public float[] getPosition(float param){
        return new float[0];
    }

    private void recalculatePathParams(){
        for(int i = 1; i < pathPoints.size(); i++){
            float l = Math.abs(VectorMath.magnitudeVec3D(VectorMath.subVec3D(pathPoints.get(i).getPosition(), pathPoints.get(i-1).getPosition())));
            this.lengths.add(l);
            this.totalLength += l;
            System.out.println("PATH LENGTH " + l);
        }

        System.out.println("TOTAL LENGTH " + totalLength);

        float paramCalc = 0.0F;
        for(float l : lengths){
            paramCalc += l;
            pathParams.add(paramCalc/totalLength);
        }
    }



}

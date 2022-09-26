package Objects;

import Tools.VectorMath;

import java.util.ArrayList;
import java.util.Arrays;

public class Path implements Thing{
    private float[] position = {0,0,0};
    private float orientation = 0;
    private int id = 0;

    public ArrayList<Point> pathPoints = new ArrayList<>();
    private ArrayList<Float> pathParams = new ArrayList<>();
    private ArrayList<Float> lengths = new ArrayList<>();
    private float totalLength = 0;

    public Path(int id, Point... args){
        this.id = id;
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
        float[] closestPoint = {0,0,0};
        float closestDistance = 9999999;
        int closestIndex = 0;

        for(int i = 0; i < pathPoints.size()-1; i++){
            Point a = pathPoints.get(i);
            Point b = pathPoints.get(i+1);

            float[] testPoint = getClosestPointOnSegment(position, a.getPosition(), b.getPosition());
            float distance = VectorMath.magnitudeVec3D(VectorMath.subVec3D(position, testPoint));

            if(distance < closestDistance){
                closestPoint = testPoint;
                closestDistance = distance;
                closestIndex = i;
            }
        }

        float[] aPoint = pathPoints.get(closestIndex).getPosition();
        float aParam = pathParams.get(closestIndex);

        float[] bPoint = pathPoints.get(closestIndex+1).getPosition();
        float bParam = pathParams.get(closestIndex+1);

        float t = VectorMath.magnitudeVec3D(VectorMath.subVec3D(closestPoint,aPoint)) / VectorMath.magnitudeVec3D(VectorMath.subVec3D(bPoint, aPoint));

        return aParam + (t * (bParam - aParam));
    }

    public float[] getPathPosition(float param){
        int i = getHighestIndex(param);
        float[] aPoint = pathPoints.get(i).getPosition();
        float[] bPoint = pathPoints.get(i+1).getPosition();

        float t = (param - pathParams.get(i)) / (pathParams.get(i+1) - pathParams.get(i));

        float[] p = VectorMath.addVec3D(aPoint, VectorMath.scalarMultiplyVec3D(VectorMath.subVec3D(bPoint,aPoint), t));

        return p;
    }

    public Path reversePath(){
        ArrayList<Point> newPath = new ArrayList<>();

        for(int i = pathPoints.size()-1; i >= 0; i--){
            newPath.add(pathPoints.get(i));
        }

        this.pathPoints = newPath;
        recalculatePathParams();

        return this;
    }
    @Override
    public float[] getPosition() {
        return this.position;
    }

    @Override
    public Path setPosition(float[] newPosition) {
        float[] deltaP = VectorMath.subVec3D(newPosition, this.position);

        return this.modifyPosition(deltaP);
    }

    @Override
    public Path modifyPosition(float[] deltaP) {
        for(Point p : pathPoints){
            p.modifyPosition(deltaP);
        }

        //this.position = VectorMath.addVec3D(this.position, deltaP);
        this.recalculatePathParams();

        return this;
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public Path setOrientation(float newOrientation) {
        float[] originalPos = this.position.clone();
        this.setPosition(new float[]{0,0,0});

        float[][] rotationMatrix = VectorMath.createRotationMatrix(new float[]{0, newOrientation,0});

        for(Point p : pathPoints){
            //System.out.println("Pre-Rotate: " + p.getPosition()[0] + " ," + p.getPosition()[2]);
            p.setPosition(VectorMath.matrixMul3D(rotationMatrix, p.getPosition()));
            //System.out.println("Post-Rotate: " + p.getPosition()[0] + " ," + p.getPosition()[2]);
        }

        this.setPosition(originalPos);
        this.orientation = newOrientation;

        return this;
    }

    @Override
    public Path modifyOrientation(float deltaO) {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    private void recalculatePathParams(){
        this.lengths.clear();
        this.lengths.add(0F);
        this.pathParams.clear();
        this.totalLength = 0F;

        if(pathPoints.size() > 0 && this.position != this.pathPoints.get(0).getPosition()){
            this.position = pathPoints.get(0).getPosition();
            this.orientation = pathPoints.get(0).getOrientation();
        }

        for(int i = 1; i < pathPoints.size(); i++){
            float l = Math.abs(VectorMath.magnitudeVec3D(VectorMath.subVec3D(pathPoints.get(i).getPosition(), pathPoints.get(i-1).getPosition())));
            this.lengths.add(l);
            this.totalLength += l;
            //System.out.println("PATH LENGTH " + l);
        }

        //System.out.println("TOTAL LENGTH " + totalLength);

        float paramCalc = 0.0F;
        for(float l : lengths){
            paramCalc += l;
            pathParams.add(paramCalc/totalLength);
        }
    }

    private float[] getClosestPointOnSegment(float[] pos, float[] a, float[] b){
        float t = VectorMath.dotVec3D(VectorMath.subVec3D(pos, a), VectorMath.subVec3D(b, a)) / VectorMath.dotVec3D(VectorMath.subVec3D(b, a), VectorMath.subVec3D(b,a));

        if(t <= 0){
            return a;
        }else if(t >= 1){
            return b;
        }else{
            float[] q = VectorMath.subVec3D(b,a);
            float[] w = VectorMath.scalarMultiplyVec3D(q, t);
            return VectorMath.addVec3D(a, w);
        }
    }

    private int getHighestIndex(float param){
        int i = 0;

        int j = 0;
        for(float f : pathParams){
            if(param > f){
                i = j;
            }
            j++;
        }

        return i;
    }
}

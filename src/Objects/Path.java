package Objects;

import Tools.VectorMath;

import java.util.ArrayList;
import java.util.Arrays;

public class Path implements Thing{
    private float[] position = {0,0,0};
    private float orientation = 0;
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
        if(pathPoints.size() > 0 && this.position != this.pathPoints.get(0).getPosition()){
            this.position = pathPoints.get(0).getPosition();
            this.orientation = pathPoints.get(0).getOrientation();
        }

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

        this.position = VectorMath.addVec3D(this.position, deltaP);

        return this;
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public Path setOrientation(float newOrientation) {
        float[] originalPos = this.position;
        this.setPosition(new float[]{0,0,0});

        float[][] rotationMatrix = VectorMath.createRotationMatrix(new float[]{0, newOrientation,0});

        for(Point p : pathPoints){
            System.out.println("Pre-Rotate: " + p.getPosition()[0] + " ," + p.getPosition()[2]);
            p.setPosition(VectorMath.matrixMul3D(rotationMatrix, p.getPosition()));
            System.out.println("Post-Rotate: " + p.getPosition()[0] + " ," + p.getPosition()[2]);
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
}

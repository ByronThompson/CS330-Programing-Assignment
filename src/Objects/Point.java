package Objects;

public class Point implements Thing{

    private float[] position;

    private float orientation = 0;
    private int id;

    public Point(int id, float[] position){
        this.id = id;
        this.position = position;
    }

    @Override
    public float[] getPosition() {
        return this.position;
    }

    @Override
    public Point setPosition(float[] newPosition) {
        this.position = newPosition;
        return this;
    }

    @Override
    public Point modifyPosition(float[] deltaP) {
        for(int i = 0; i < this.position.length; i++){
            this.position[i] += deltaP[i];
        }
        return this;
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public Point setOrientation(float newOrientation) {
        this.orientation = newOrientation;
        return this;
    }

    @Override
    public Point modifyOrientation(float deltaO) {
        this.orientation += deltaO;
        return this;
    }

    @Override
    public int getId() {
        return 0;
    }
}

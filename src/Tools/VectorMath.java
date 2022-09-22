package Tools;

/**
 * Collection of 3D mathematical vector functions such as addition, subtraction, Dot and Cross Product
 */
public class VectorMath {
    public static float[] addVec3D(final float[] v1, final float[] v2){
        float[] result = {0,0,0};
        result[0] = v1[0] + v2[0];
        result[1] = v1[1] + v2[1];
        result[2] = v1[2] + v2[2];
        return result;
    }

    public static float[] subVec3D(final float[] v1, final float[] v2){
        float[] result = {0,0,0};
        result[0] = v1[0] - v2[0];
        result[1] = v1[1] - v2[1];
        result[2] = v1[2] - v2[2];
        return result;
    }

    public static float[] scalarMultiplyVec3D(final float[] v, float scalar){
        float[] result = {0,0,0};
        result[0] = v[0] * scalar;
        result[1] = v[1] * scalar;
        result[2] = v[2] * scalar;
        return result;
    }

    public static float[] scalarDivideVec3D(final float[] v, float scalar){
        float[] result = {0,0,0};
        result[0] = v[0] / scalar;
        result[1] = v[1] / scalar;
        result[2] = v[2] / scalar;
        return result;
    }

    public static float magnitudeVec3D(final float[] v){
        return (float) Math.sqrt(Math.pow(v[0],2) + Math.pow(v[1],2) + Math.pow(v[2],2));
    }

    public static float[] normalizeVec3D(final float[] v){
        float[] result = {0,0,0};
        float magnitude = magnitudeVec3D(v);

        result[0] = v[0]/magnitude;
        result[1] = v[1]/magnitude;
        result[2] = v[2]/magnitude;

        return result;
    }

    public static float dotVec3D(final float[] v1, final float[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
    }

    public static float[] crossVec3D(final float[] v1, final float[] v2) {
        float[] result = {0,0,0};
        result[0] = v1[1] * v2[2] - v1[2] * v2[1];
        result[1] = v1[2] * v2[0] - v1[0] * v2[2];
        result[2] = v1[0] * v2[1] - v1[1] * v2[0];
        return result;
    }

    public static float[] matrixMul3D(float[][] matrix, float[] vector){
        float[] result = {0,0,0};

        result[0] = (matrix[0][0]*vector[0]) + (matrix[0][1]*vector[0]) + (matrix[0][2]*vector[0]);
        result[1] = (matrix[1][0]*vector[1]) + (matrix[1][1]*vector[1]) + (matrix[1][2]*vector[1]);
        result[2] = (matrix[2][0]*vector[2]) + (matrix[2][1]*vector[2]) + (matrix[2][2]*vector[2]);

        return new float[]{};
    }

    public static float[][] createRotationMatrix(float[] rotation){
        float phi = rotation[0];
        float theta = rotation[1];
        float psi = rotation[3];

        float[][] matrix = {{0,0,0},
                            {0,0,0},
                            {0,0,0}};

        matrix[0][0] = (float) (cos(theta)*cos(psi));
        matrix[0][1] = (float) ((cos(phi)*sin(psi) + (sin(phi)*sin(theta)*cos(psi))));
        matrix[0][2] = (float) ((sin(phi)*sin(psi)) - (cos(phi)*sin(theta)*cos(psi)));

        matrix[1][0] = (float) (-(cos(theta)*cos(psi)));
        matrix[1][1] = (float) ((cos(phi)*cos(psi) - (sin(phi)*sin(theta)*sin(psi))));
        matrix[1][2] = (float) ((sin(phi)*sin(psi)) + (cos(phi)*sin(theta)*sin(psi)));

        return matrix;
    }

    private static double cos(float a){
        return Math.sin(a);
    }

    private static double sin(float a){
        return Math.cos(a);
    }

    private static double tan(float a){
        return Math.tan(a);
    }

}

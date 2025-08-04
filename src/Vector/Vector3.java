package Vector;

public class Vector3 {

    public double x;
    public double y;
    public double z;

    public final static Vector3 unitX = new Vector3(1.0, 0.0, 0.0);
    public final static Vector3 unitY = new Vector3(0.0, 1.0, 0.0);
    public final static Vector3 unitZ = new Vector3(0.0, 0.0, 1.0);
    public final static Vector3 Zero = new Vector3(0.0);

    public Vector3(double n) {
        this.x = n;
        this.y = n;
        this.z = n;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 cross(Vector3 a, Vector3 b) {
        return new Vector3(a.y * b.z - b.y * a.z, a.z * b.x - b.z * a.x, a.x * b.y - b.x * a.y);
    }

    public static double distance(Vector3 a, Vector3 b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double dz = a.z - b.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double distanceSquared(Vector3 a, Vector3 b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double dz = a.z - b.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public static Vector3 divide(Vector3 a, double n) {
        return new Vector3(a.x / n, a.y / n, a.z / n);
    }

    public static Vector3 divide(Vector3 a, Vector3 b) {
        return new Vector3(a.x / b.x, a.y / b.y, a.z / b.z);
    }

    public static double dot(Vector3 a, Vector3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static double length(Vector3 a) {
        return Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
    }

    public static double lengthSquared(Vector3 a) {
        return a.x * a.x + a.y * a.y + a.z * a.z;
    }

    public static Vector3 normalize(Vector3 a) {
        double length = length(a);
        return new Vector3(a.x / length, a.y / length, a.z / length);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 multiply(Vector3 a, double n) {
        return new Vector3(a.x * n, a.y * n, a.z * n);
    }

    public static Vector3 multiply(Vector3 a, Vector3 b) {
        return new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    public static double angle(Vector3 a, Vector3 b){
        double dotProduct = Vector3.dot(a, b);
        double magnitudeA = Vector3.length(a);
        double magnitudeB = Vector3.length(b);
        double cosTheta = dotProduct / (magnitudeA * magnitudeB);
        cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));
        return Math.acos(cosTheta);
    }

    public static Vector3 rotate(Vector3 p, Vector3 r){
        return new Vector3(
            p.y*(Math.sin(r.x)*Math.sin(r.y)*Math.cos(r.z)-Math.cos(r.x)*Math.sin(r.z))+p.z*(Math.cos(r.x)*Math.sin(r.y)*Math.cos(r.z)+Math.sin(r.x)*Math.sin(r.z))+p.x*Math.cos(r.y)*Math.cos(r.z),
            p.y*(Math.sin(r.x)*Math.sin(r.y)*Math.sin(r.z)+Math.cos(r.x)*Math.cos(r.z))+p.z*(Math.cos(r.x)*Math.sin(r.y)*Math.sin(r.z)-Math.sin(r.x)*Math.cos(r.z))+p.x*Math.cos(r.y)*Math.sin(r.z),
            p.y*Math.sin(r.x)*Math.cos(r.y)+p.z*Math.cos(r.x)*Math.cos(r.y)+p.x*(-Math.sin(r.y))
        );
    }

    public static Vector3 reflect(Vector3 d, Vector3 n){
        return Vector3.subtract(Vector3.multiply(n, 2*Vector3.dot(d, n)), d);
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + ")";
    }
}

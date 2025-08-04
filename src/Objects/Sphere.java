package Objects;

import Vector.Vector3;

public class Sphere extends Mesh{

    double radius;
    Vector3 center;

    public Sphere(double radius, Vector3 center, RGB color){
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public void setCenter(Vector3 center) {
        this.center = center;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    
    public Vector3 getNormal(Vector3 p){
        Vector3 normal = Vector3.subtract(p, center);
        return Vector3.normalize(normal);
    }
    
}

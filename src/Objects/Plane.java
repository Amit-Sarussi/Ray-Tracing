package Objects;

import Vector.Vector3;

public class Plane extends Mesh{
    
    public double a;
    public double b;
    public double c;
    public double d;


    public Plane(double a, double b, double c, double d, RGB color){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.color = color;
    }

    public Vector3 getNormal(Vector3 p){
        Vector3 normal = new Vector3(a, b, c);
        return Vector3.normalize(normal);
    }
}

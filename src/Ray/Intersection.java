package Ray;

import Objects.Mesh;
import Vector.Vector3;

public class Intersection {
    
    Vector3 pos;
    Mesh mesh;

    public Intersection(Vector3 pos, Mesh mesh){
        this.pos = pos;
        this.mesh = mesh;
    }

    public Vector3 getPos() {
        return pos;
    }

    public Mesh getMesh() {
        return mesh;
    }
}

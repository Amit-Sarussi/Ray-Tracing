package Objects;

import Vector.Vector3;

public class Light {
    Vector3 pos;
    RGB color;

    public Light(Vector3 pos, RGB color){
        this.pos = pos;
        this.color = color;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    public RGB getColor() {
        return color;
    }

    public void setColor(RGB color) {
        this.color = color;
    }
}

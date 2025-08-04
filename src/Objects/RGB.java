package Objects;

public class RGB {
    double r;
    double g;
    double b;

    public RGB(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGB(double n){
        this.r = n;
        this.g = n;
        this.b = n;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public RGB mul(double scalar){
        return new RGB(r*scalar, g*scalar, b*scalar);
    }

    public RGB mul(RGB c){
        return new RGB(r*c.r, g*c.g, b*c.b);
    }

    public RGB add(RGB c){
        return new RGB(r+c.r, g+c.g, b+c.b);
    }

    public RGB add(double c){
        return new RGB(r+c, g+c, b+c);
    }

    public RGB clamp(){
        return new RGB(
            Math.min(1, Math.max(0, r)),
            Math.min(1, Math.max(0, g)),
            Math.min(1, Math.max(0, b))
        );
    }
    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ")";
    }
}

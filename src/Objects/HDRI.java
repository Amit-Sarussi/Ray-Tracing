package Objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Vector.Vector3;

public class HDRI extends Mesh{

    double radius;
    Vector3 center;
    String imagePath;
    int width;
    int height;
    BufferedImage image;

    public HDRI(double radius, Vector3 center, String imagePath) throws IOException {
        this.center = center;
        this.radius = radius;
        this.image = ImageIO.read(new File(imagePath));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    public RGB getUVColor(Vector3 p){
        return new RGB(0, 0, 0);
    }
    
    public static Vector3 cartesianToSpherical(Vector3 p){
        double r = Math.sqrt(p.x*p.x + p.y*p.y + p.z*p.z);
        double azimuth = Math.atan2(p.y, p.x);
        double zenith = Math.acos(p.z/r);
        return new Vector3(r, azimuth, zenith);
    }

    public static Vector3 sphericalToEquirectangular(Vector3 p, int width, int height){
        Vector3 spherical_point = cartesianToSpherical(p);

        double azimuth = spherical_point.y;
        double zenith = spherical_point.z;
        int x = (int) ((azimuth+Math.PI)*width/(2*Math.PI));
        int y = (int) ((Math.PI/2-zenith)*height/Math.PI);
        return new Vector3(x, y, 0);
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
    
}

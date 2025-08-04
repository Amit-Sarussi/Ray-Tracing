package Ray;

import Objects.Camera;
import Objects.HDRI;
import Objects.Mesh;
import Objects.Plane;
import Objects.RGB;
import Objects.Sphere;
import Vector.Vector3;
import WorldSpace.World;

public class RayCaster {

    public static RGB color(Intersection hit, World world){
        RGB c = hit.getMesh().getColor();
        

        Intersection lightRay = castRay(hit.getPos(), world.getLight().getPos(), world);
        if (lightRay == null || (Vector3.distance(lightRay.getPos(), hit.getPos()) > Vector3.distance(world.getLight().getPos(), hit.getPos()))){
            Vector3 light = Vector3.subtract(world.getLight().getPos(), hit.getPos());
            Vector3 normLight = Vector3.normalize(light);
            Vector3 normal = hit.getMesh().getNormal(hit.getPos());
            if (normal != null){
                double angle = Vector3.angle(normLight, normal);
                
                double cfs = calcShadowCFS(angle);
                return new RGB((int) ((double)c.getR()*cfs), (int) ((double)c.getG()*cfs), (int) ((double)c.getB()*cfs));
            }
            return c;
        }
        return new RGB(0, 0, 0);
    }

    public static double calcShadowCFS(double angle){
        angle = angle*180/Math.PI;
        if (angle <= 45) return 1;
        angle -= 45;
        angle = 45 - angle;
        return angle/45;
    }

    public static RGB phongReflectionModel(Intersection hit, World world, Camera camera){
        RGB objectColor = hit.getMesh().getColor();

        // Get the ambient color
        double ambient_strength = 0.1;
        RGB Ambient = world.getLight().getColor().mul(ambient_strength);

        // Get the diffuse color
        Vector3 normal = Vector3.normalize(hit.getMesh().getNormal(hit.getPos()));
        Vector3 light = Vector3.subtract(world.getLight().getPos(), hit.getPos());
        Vector3 normLight = Vector3.normalize(light);
        double diff_strength = Math.max(Vector3.dot(normal, normLight), 0);
        RGB Diffuse = world.getLight().getColor().mul(diff_strength);

        // Get the specular color
        double specular_strength = 1;
        Vector3 view = Vector3.normalize(Vector3.subtract(camera.getCameraPos(), hit.getPos()));
        Vector3 reflect = Vector3.reflect(normLight, normal);
        double spec = Math.pow(Math.max(Vector3.dot(reflect, view), 0), 32);
        RGB Specular = world.getLight().getColor().mul(spec*specular_strength);

        return Ambient.add(Diffuse).add(Specular).mul(objectColor);
    }


    public static Intersection castRay(Vector3 p1, Vector3 p2, World w){
        Vector3 direction = Vector3.subtract(p2, p1);
        double smallest_t = -1;
        Mesh closest_mesh = null;

        for (Mesh m : w.getMeshes()){
            // Check for intersection between the ray and the sphere
            if (m instanceof Sphere) {
                Sphere sphere = (Sphere) m;
                Vector3 center = sphere.getCenter();
                double radius = sphere.getRadius();
                Vector3 oc = Vector3.subtract(p1, center); // Vector from the sphere center to the ray origin

                // Calculate discriminant for the quadratic equation
                double a = Vector3.lengthSquared(direction);
                double b = 2 * Vector3.dot(oc, direction); 
                double c = Vector3.lengthSquared(oc) - radius * radius;

                double determinant = b * b - 4 * a * c; // Discriminant

                if (determinant >= 0) { // If the ray intersects the sphere
                    double t1 = (-b + Math.sqrt(determinant)) / (2 * a); // First intersection point
                    double t2 = (-b - Math.sqrt(determinant)) / (2 * a); // Second intersection point
                    double t_min = min(t1, t2); // The smallest value of both intersection points

                    if (smallest_t == -1) { // If this is the first intersection
                        smallest_t = t_min; // Set the cloesest intersection point as the first intersection
                        closest_mesh = m; // Set the closest intersection mesh
                    } else if (smallest_t > t_min) { // If the new intersection point is closer than the current one
                        smallest_t = t_min; // Set the smallest intersection point as the closest
                        closest_mesh = m; // Set the closest intersection mesh
                    }
                }
            }
            else if (m instanceof HDRI){
                HDRI sphere = (HDRI) m;
                Vector3 s = sphere.getCenter();
                double a = direction.x*direction.x + direction.y*direction.y + direction.z*direction.z;
                double b = 2*(p1.x*direction.x + p1.y*direction.y + p1.z*direction.z - s.x*direction.x -s.y*direction.y -s.z*direction.z);
                double c = (p1.x-s.x)*(p1.x-s.x) + (p1.y-s.y)*(p1.y-s.y) + (p1.z-s.z)*(p1.z-s.z) -sphere.getRadius()*sphere.getRadius();
                double determinant = b*b - 4*a*c;

                if (determinant >= 0){
                    double t1 = (-b + Math.sqrt(determinant)) / (2 * a);
                    double t2 = (-b - Math.sqrt(determinant)) / (2 * a);
    
                    if (t1 < 0.00000000001 && t2 < 0.00000000001) continue;
                    
                    if (smallest_t == -1){
                        smallest_t = min(t1,t2);
                        closest_mesh = m;
                    }
                    else if (smallest_t > min(t1,t2)){
                        smallest_t = Math.min(min(t1,t2), smallest_t);
                        closest_mesh = m;
                    }
                    
                }
               
            }
            else if (m instanceof Plane){
                Plane plane = (Plane) m;
                double numerator = -(plane.a*p1.x + plane.b*p1.y + plane.c*p1.z + plane.d);
                double denominator = plane.a*direction.x + plane.b*direction.y + plane.c*direction.z;
                
                if (denominator != 0){
                    double t_ = numerator/denominator;
                    if (t_ >= 0.00000000001 && (t_ < smallest_t || smallest_t == -1)){
                        smallest_t = t_;
                        closest_mesh = m;
                    }
                }
            }
        }


        if (smallest_t == -1)
            return null;
        else
            return new Intersection(
                Vector3.add(p1, Vector3.multiply(direction, smallest_t)), 
                closest_mesh
            );
    }

    public static double min(double n, double m){
        if (n < 0) return m;
        if (m < 0) return n;
        return Math.min(n,m);
    }
}

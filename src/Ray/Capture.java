package Ray;

import Objects.Camera;
import Objects.HDRI;
import Objects.RGB;
import Vector.Vector3;
import WorldSpace.World;

public class Capture {

    public static Vector3 centerNearPlane(Camera cam){
        Vector3 addition = Vector3.rotate(
            new Vector3(cam.getNearPlaneDist(), 0, 0), 
            cam.getRotation()
        );
        return Vector3.add(addition, cam.getCameraPos());
    }
    
    public static RGB[][] capture(int WIDTH, int HEIGHT, Camera camera, World world){
        RGB[][] frameBuffer = new RGB[HEIGHT][WIDTH];

        Vector3 p = Capture.centerNearPlane(camera);

        for (int i = 0; i< HEIGHT; i++){
            for (int j = 0; j< WIDTH; j++){

                //Find point
                double ux = 0.5 - j / (double)WIDTH;
                double uy = 0.5 - i / (double)HEIGHT;
                
                Vector3 dnp = Vector3.rotate(
                    new Vector3(0, camera.getNearPlaneWidth()*ux, camera.getNearPlaneHeight()*uy), 
                    camera.getRotation()
                );
                Vector3 npPoint = Vector3.add(dnp, p);
                Intersection hit = RayCaster.castRay(
                    camera.getCameraPos(), npPoint, 
                    world
                );
                
                if (hit == null) frameBuffer[i][j] = camera.getSky();
                else if (hit.getMesh() instanceof HDRI){
                    HDRI sky = (HDRI) hit.getMesh();
                    frameBuffer[i][j] = sky.getUVColor(hit.getPos()).clamp();
                }    
                else{
                    frameBuffer[i][j] = RayCaster.phongReflectionModel(hit, world, camera).clamp();
                }
            }
        }

        return frameBuffer;
    }
}

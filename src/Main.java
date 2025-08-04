import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import Graphics.Frame;
import Objects.Camera;
import Objects.HDRI;
import Objects.Light;
import Objects.Mesh;
import Objects.Plane;
import Objects.RGB;
import Objects.Sphere;
import Ray.Capture;
import Vector.Vector3;
import WorldSpace.World;

public class Main {
    public static void main(String[] args) throws IOException {
        Sphere sphere = new Sphere(0.5, new Vector3(3,0,0.5), new RGB(1,0.2,0.4));
        Sphere sphere2 = new Sphere(0.2, new Vector3(3,1,0.5), new RGB(0,0.2,1));
        Sphere sphere3 = new Sphere(0.2, new Vector3(3,-1,0.5), new RGB(0,1,0.2));
        HDRI sky = new HDRI(200, new Vector3(0,0,0), 
        "src/Graphics/HDRI/sky.jpg"
        );
        Mesh[] meshes = new Mesh[]{
            sky,
            sphere,
            sphere2,
            sphere3,
            new Plane(0,0,1,0, new RGB(0.7)),
        };

        Light light = new Light(new Vector3(-70,70,70), new RGB(2));
        World world = new World(meshes, light);
        Camera camera = new Camera(
            new Vector3(0,0,0.5), 
            0.7, 1.1, 
            (double) 16/9, new Vector3(0,0,0),
            new RGB(0, 0, 0)
        );

        int WIDTH = 1200;
        int HEIGHT = 675;

        
        RGB[][] frameBuffer = Capture.capture(WIDTH, HEIGHT, camera, world);
        Frame f = new Frame(WIDTH, HEIGHT);
        
        f.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    camera.addQueuePosition(new Vector3(0.1, 0, 0));
                } else if (keyCode == KeyEvent.VK_A) {
                    camera.addQueuePosition(new Vector3(0, 0.1, 0));
                } else if (keyCode == KeyEvent.VK_S) {
                    camera.addQueuePosition(new Vector3(-0.1, 0, 0));
                } else if (keyCode == KeyEvent.VK_D) {
                    camera.addQueuePosition(new Vector3(0, -0.1, 0));
                } else if (keyCode == KeyEvent.VK_UP) {
                    camera.addQueueRotation(new Vector3(0, -0.1, 0));
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    camera.addQueueRotation(new Vector3(0, 0.1, 0));
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    camera.addQueueRotation(new Vector3(0, 0, 0.1));
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    camera.addQueueRotation(new Vector3(0, 0, -0.1));
                }
            }
        });
    
        while (true){
            f.getCanvas().setFrame(frameBuffer);
            frameBuffer = Capture.capture(WIDTH, HEIGHT, camera, world);
            camera.pushUpdate();
        }

         
    }
}


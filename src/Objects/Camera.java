package Objects;

import Vector.Vector3;

public class Camera {

    Vector3 cameraPos;
    double nearPlaneDist;
    double nearPlaneWidth;
    double nearPlaneHeight;
    double aspectRatio;
    Vector3 rotation;
    Vector3 queueRotation;
    Vector3 queuePosition;
    RGB Sky;

    // public Camera(double nearPlaneDist, double nearPlaneWidth, double aspectRatio){
    //     this.cameraPoint = new Point(0, 0, 0);
    //     this.nearPlaneDist = nearPlaneDist;
    //     this.nearPlaneWidth = nearPlaneWidth;
    //     this.aspectRatio = aspectRatio;
    //     this.nearPlaneHeight = nearPlaneWidth / aspectRatio;
    // }

    public Camera(Vector3 cameraPos, double nearPlaneDist, double nearPlaneWidth, 
                    double aspectRatio, Vector3 rotation, RGB Sky){
        this.cameraPos = cameraPos;
        this.nearPlaneDist = nearPlaneDist;
        this.nearPlaneWidth = nearPlaneWidth;
        this.aspectRatio = aspectRatio;
        this.nearPlaneHeight = nearPlaneWidth / aspectRatio;
        this.rotation = rotation;
        this.Sky = Sky;
        this.queuePosition = new Vector3(0,0,0);
        this.queueRotation = new Vector3(0,0,0);
    }

    public void addQueuePosition(Vector3 position){
        this.queuePosition = Vector3.add(this.queuePosition, position);
    }

    public void addQueueRotation(Vector3 rotation){
        this.queueRotation = Vector3.add(this.queueRotation, rotation);
    }

    public void pushUpdate(){
        this.cameraPos = Vector3.add(this.cameraPos, this.queuePosition);
        this.rotation = Vector3.add(this.rotation, this.queueRotation);
        this.queuePosition = new Vector3(0,0,0);
        this.queueRotation = new Vector3(0,0,0);
    }
    
    public Vector3 getCameraPos() {
        return cameraPos;
    }

    public void setCameraPos(Vector3 cameraPos) {
        this.cameraPos = cameraPos;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public void setRotation(Vector3 rotation){
        this.rotation = rotation;
    }

    public double getNearPlaneWidth() {
        return nearPlaneWidth;
    }

    public double getNearPlaneDist() {
        return nearPlaneDist;
    }

    public double getNearPlaneHeight() {
        return nearPlaneHeight;
    }

    public RGB getSky() {
        return Sky;
    }
    
}

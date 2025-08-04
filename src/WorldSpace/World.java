package WorldSpace;

import Objects.Light;
import Objects.Mesh;

public class World {
    
    Mesh[] meshes;
    Light light;

    public World(Mesh[] meshes, Light light){
        this.meshes = meshes;
        this.light = light;
    }

    public Mesh[] getMeshes() {
        return meshes;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }
}

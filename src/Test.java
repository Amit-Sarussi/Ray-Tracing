import java.io.IOException;

import Vector.Vector3;

public class Test {
    
    public static void main(String[] args) throws IOException {
        Vector3 a = new Vector3(1, 2, 3);
        Vector3 b = new Vector3(4,5,6);
        System.out.println(Vector3.reflect(a, b));
        
    }
}

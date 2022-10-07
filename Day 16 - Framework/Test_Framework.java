import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;

public class Test_Framework extends Base
{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;

    public static void main(String[] args)
    {
        Base test = new Test_Framework();
        test.setWindowSize(640, 640);
        test.run();
    }
    
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        camera.setPosition( new Vector(0,0,3) );
        Geometry geometry = new BoxGeometry();
        Material material = new SurfaceMaterial();
        // to change value from default, for example:
        material.uniforms.get("useVertexColors").data = 1;
        // material.renderSettings.get("pointSize").data = 32;
        // material.renderSettings.get("lineWidth").data = 8;
        // material.renderSettings.get("wireframe").data = true;
        mesh = new Mesh( geometry, material );
        scene.add( mesh );
    }

    public void update()
    {
        mesh.rotateY( 0.0123f, true );
        mesh.rotateX( 0.0237f, true );
        renderer.render(scene, camera);
    }


}

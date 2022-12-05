import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.glfw.GLFW.*;

import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.extras.*;

public class Test_Texture extends Base
{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public MovementRig rig;

    public static void main(String[] args)
    {
        Base test = new Test_Texture();
        test.setWindowSize(800, 800);
        test.run();
    }

    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        // camera.setPosition( new Vector(0.5, 1, 4) );
        
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0.5, 1, 4) );
        scene.add( rig );
 
        Geometry geometry = new BoxGeometry(); 
        Material material = new TextureMaterial( new Texture("images/grid.png") );
        mesh = new Mesh(geometry, material);
        mesh.translate( 0,1,0, true );
        scene.add(mesh);
        
        Mesh grid = new GridHelper(100,100, new Vector(0.5,0.2,0.5), new Vector(1,1,1), 2);
        grid.rotateX(-Math.PI/2, true);
        scene.add( grid );

        Mesh axes = new AxesHelper(2, 8);
        axes.translate(0, 0.01, 0, true);
        scene.add( axes );

    }

    public void update()
    {
        rig.update(input, deltaTime);
        
        mesh.rotateX(0.01337, true);
        mesh.rotateY(0.02357, true);

        renderer.render(scene, camera);
    }
}

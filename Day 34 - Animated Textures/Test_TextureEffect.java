import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.glfw.GLFW.*;

import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.extras.*;

public class Test_TextureEffect extends Base
{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public MovementRig rig;

    public static void main(String[] args)
    {
        Base test = new Test_TextureEffect();
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
 
        Geometry geometry = new PlaneGeometry(); 

        // creating a custom Material within application
        // animated effect, using time (must set in update method)
        
        Texture gridTex  = new Texture("images/grid.png");
        // Texture crateTex = new Texture("images/crate.png");
        // Texture noiseTex = new Texture("images/noise.png");
        
        Material material = new Material(
            "graphics/shaders/basic-uv.vert", 
            "graphics/shaders/ripple.frag"); // ripple, blend, distort

        // time uniform required for all animated shaders
        material.addUniform("float", "time", 0.0f);

        // uniform for ripple shader only
        material.addUniform("sampler2D", "texture", new Vector(gridTex.textureRef, 1));
        
        // uniforms for blend shader only
        // material.addUniform("sampler2D", "texture1", new Vector(gridTex.textureRef, 1));
        // material.addUniform("sampler2D", "texture2", new Vector(crateTex.textureRef, 2));
        
        // uniforms for distort shader only
        // material.addUniform("sampler2D", "image", new Vector(gridTex.textureRef, 1));
        // material.addUniform("sampler2D", "noise", new Vector(noiseTex.textureRef, 2));
        
        // after adding uniform variables, always must locate variable references
        material.locateUniforms();
        
        mesh = new Mesh(geometry, material);
        mesh.translate( 0, 1, 0.1, true );
        scene.add(mesh);
        
        Mesh grid = new GridHelper(100,100, 
            new Vector(0.5,0.2,0.5), new Vector(1,1,1), 2);
        grid.rotateX(-Math.PI/2, true);
        scene.add( grid );

        Mesh axes = new AxesHelper(2, 8);
        axes.translate(0, 0.01, 0, true);
        scene.add( axes );

    }

    public void update()
    {
        rig.update(input, deltaTime);
        
        // need to update time!
        mesh.material.uniforms.get("time").data = time;
        
        renderer.render(scene, camera);
    }
}

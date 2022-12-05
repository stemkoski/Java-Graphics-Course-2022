import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.glfw.GLFW.*;

import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.extras.*;

public class Test_Rig extends Base
{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh, mesh2, mesh3, mesh4, mesh5, mesh6;
    public MovementRig rig;
    public Group group;
    
    public static void main(String[] args)
    {
        Base test = new Test_Rig();
        test.setWindowSize(800, 800);
        test.run();
    }

    public Material rectangleMat;
    
    public void initialize()
    {
        renderer = new Renderer();
        scene    = new Scene();
        camera   = new Camera();
        //camera.setPosition( new Vector(0.5, 1, 4) );

        group = new Group();
        
        // Rectangle Set up
        Geometry rectangleGeo = new RectangleGeometry();
        
        // Front Square
        rectangleMat = new SurfaceMaterial();
        
        System.out.println("ASDF");
        System.out.println( rectangleMat.uniforms.get("baseColor").data );
        System.out.println( rectangleMat.uniforms.get("baseColor").variableRef );
 
        // rectangleMat.uniforms.get("baseColor").data = new Vector(1,0,0);
        rectangleMat.addUniform("vec3", "baseColor", new Vector (1,0,0));
        System.out.println( rectangleMat.uniforms.get("baseColor").data );
        System.out.println( rectangleMat.uniforms.get("baseColor").variableRef );
        
        mesh = new Mesh( rectangleGeo, rectangleMat );
        mesh.setPosition(new Vector(0, 1, 0.5));
        group.add( mesh );
        
        /*
        // Right Square
        Material rectangleMat2 = new SurfaceMaterial();
        rectangleMat2.addUniform("vec3", "baseColor", new Vector (1,0.5,0));
        mesh2 = new Mesh( rectangleGeo, rectangleMat2 );
        mesh2.setPosition(new Vector(0.5, 1, 0));
        mesh2.rotateY(Math.PI/2, true);
        group.add( mesh2 );
        
        // Left Square
        Material rectangleMat3 = new SurfaceMaterial();
        rectangleMat3.addUniform("vec3", "baseColor", new Vector (0,0,1));
        mesh3 = new Mesh( rectangleGeo, rectangleMat3 );
        mesh3.setPosition(new Vector(-0.5,1, 0));
        mesh3.rotateY(-Math.PI/2, true);
        group.add( mesh3 );
        
        // Back Square
        Material rectangleMat4 = new SurfaceMaterial();
        rectangleMat4.addUniform("vec3", "baseColor", new Vector (0,1,0));
        mesh4 = new Mesh( rectangleGeo, rectangleMat4 );
        mesh4.setPosition(new Vector(0, 1, -0.5));
        group.add( mesh4 );
        
        // Top Square
        Material rectangleMat5 = new SurfaceMaterial();
        rectangleMat5.addUniform("vec3", "baseColor", new Vector (1,1,0));
        mesh5 = new Mesh( rectangleGeo, rectangleMat5 );
        mesh5.setPosition(new Vector(0, 1.5, 0));
        mesh5.rotateX(-Math.PI/2, true);
        group.add( mesh5 );
        
        // Back Square
        Material rectangleMat6 = new SurfaceMaterial();
        rectangleMat6.addUniform("vec3", "baseColor", new Vector (1,0,1));
        mesh6 = new Mesh( rectangleGeo, rectangleMat6 );
        mesh6.setPosition(new Vector(0, 0.5, 0));
        mesh6.rotateX(-Math.PI/2, true);
        group.add( mesh6 );
        */
        
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0.5, 1, 4) );
        scene.add( rig );

        //              Grid Size             Line Color         Axis Color
        //Mesh grid = new GridHelper(100,100, new Vector(0.5,0.5,0.2), new Vector(1,1,1), 2);
        //grid.rotateX(-Math.PI/2, true);
        //scene.add( grid );
        
        
        Mesh grid2 = new GridHelper(100,100, new Vector(0.5,0.2,0.5), new Vector(1,1,1), 2);
        grid2.rotateX(-Math.PI/2, true);
        scene.add( grid2 );

        Mesh axes = new AxesHelper(2, 8);
        axes.translate(0, 0.01, 0, true);
        scene.add( axes );
        
       
        scene.add(group);
    }

    public void update()
    {
        //System.out.println( rectangleMat.uniforms.get("baseColor").data );
        rig.update(input, deltaTime);
        
        group.rotateY(0.01f, true);
        group.rotateX(0.05f, false);
        group.rotateZ(0.03f, true);
        
        renderer.render(scene, camera);
    }

}

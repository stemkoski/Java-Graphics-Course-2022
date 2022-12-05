import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class Test_Compile extends Base
{
    public int programRef;

    public static void main(String[] args)
    {
        Base test = new Test_Compile();
        test.setWindowSize(800, 600);
        test.run();
    }
    
    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/origin.vert", 
            "graphics/shaders/yellow.frag" );

        System.out.println("Compilation successful.");
        System.out.println("Program reference: " + programRef);
    }

    public void update()
    {

    }

}

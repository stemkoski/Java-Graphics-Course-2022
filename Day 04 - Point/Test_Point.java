import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class Test_Point extends Base
{
    public int programRef;

    public static void main(String[] args)
    {
        Base test = new Test_Point();
        test.setWindowSize(800, 600);
        test.run();
    }

    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/origin.vert", 
            "graphics/shaders/yellow.frag" );

        // set up vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        // render settings (optional)

        // set point width and height
        glPointSize(10);
    }

    public void update()
    {
        // select program to use when rendering
        glUseProgram( programRef );

        // render geometric objects using selected program
        glDrawArrays(GL_POINTS, 0, 1);
    }

}

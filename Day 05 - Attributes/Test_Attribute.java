import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

public class Test_Attribute extends Base
{
    public int programRef;

    public static void main(String[] args)
    {
        Base test = new Test_Attribute();
        test.setWindowSize(800, 600);
        test.run();
    }

    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/position.vert", 
            "graphics/shaders/yellow.frag" );

        // set up vertex array object
        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        // send data 
        float[] positionData = {
                 0.8f,  0.0f, 0.0f,
                 0.4f,  0.6f, 0.0f,
                -0.4f,  0.6f, 0.0f,
                -0.8f,  0.0f, 0.0f,
                -0.4f, -0.6f, 0.0f,
                 0.4f, -0.6f, 0.0f  };
                 
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        
        positionAttribute.associateVariable( programRef, "position" );
        
        // render settings (optional)

        // set point width and height
        glPointSize(10);
    }

    public void update()
    {
        // select program to use when rendering
        glUseProgram( programRef );

        // render geometric objects using selected program
        glDrawArrays(GL_POINTS, 0, 6);
    }

}

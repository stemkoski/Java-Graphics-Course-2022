import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_VertexColor extends Base
{
    public int programRef;
    public int vaoRef;

    public static void main(String[] args)
    {
        Base test = new Test_VertexColor();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/position-color.vert", 
            "graphics/shaders/color.frag" );

        // set up vertex array object
        vaoRef = glGenVertexArrays();
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

        float[] colorData = {
                1.0f, 0.0f, 0.0f,
                1.0f, 0.5f, 0.0f,
                1.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f,
                0.5f, 0.0f, 1.0f  };

        Attribute colorAttribute = new Attribute( "vec3", colorData );

        colorAttribute.associateVariable( programRef, "vertexColor" );

        // render settings (optional)
        glPointSize(20);
        glLineWidth(10);
    }

    public void update()
    {
        // select program to use when rendering
        glUseProgram( programRef );
        glBindVertexArray(vaoRef);
        // render geometric objects using selected program
        glDrawArrays(GL_POINTS, 0, 6);
    }

}

import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

// key constants
import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

public class Test_Input extends Base
{
    public int programRef;
    public int vaoRef;

    public Uniform<Vector> translation, baseColor;

    public float speed = 0.5f;
    
    public static void main(String[] args)
    {
        Base test = new Test_Input();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/uniform-translation.vert", 
            "graphics/shaders/uniform-color.frag" );

        // set up vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = {
                0.0f,  0.02f, 0.0f,
                0.02f, -0.02f, 0.0f,
                -0.02f, -0.02f, 0.0f  };
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        positionAttribute.associateVariable( programRef, "position" );

        // set up uniforms
        translation = new Uniform<Vector>("vec3", new Vector(0.0f, 0.0f, 0.0f) );
        translation.locateVariable( programRef, "translation" );

        Vector c = Vector.HSV_to_RGB(0, 1, 1);
        
        baseColor = new Uniform<Vector>("vec3", c );
        baseColor.locateVariable( programRef, "baseColor" );

        // render settings (optional)
        glPointSize(20);
        glLineWidth(10);
    }

    public void update()
    {
        glClear(GL_COLOR_BUFFER_BIT);

        // select program to use when rendering
        glUseProgram( programRef );
        glBindVertexArray(vaoRef);

        float distance = speed * deltaTime;
        if (input.isKeyPressed(GLFW_KEY_LEFT))
            translation.data.values[0] -= distance;
        if (input.isKeyPressed(GLFW_KEY_RIGHT))
            translation.data.values[0] += distance;
        if (input.isKeyPressed(GLFW_KEY_DOWN))
            translation.data.values[1] -= distance;
        if (input.isKeyPressed(GLFW_KEY_UP))
            translation.data.values[1] += distance;
            
        if (input.isKeyPressed(GLFW_KEY_R))
            baseColor.data = Vector.HSV_to_RGB(1.00, 1, 1);
        if (input.isKeyPressed(GLFW_KEY_G))
            baseColor.data = Vector.HSV_to_RGB(0.33, 1, 1);
        
        // update the uniforms
        translation.uploadData();
        baseColor.uploadData();
        
        // draw the triangle
        glDrawArrays( GL_TRIANGLES, 0, 3 );

    }
    
}

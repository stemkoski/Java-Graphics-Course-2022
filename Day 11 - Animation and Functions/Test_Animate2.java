import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_Animate2 extends Base
{
    public int programRef;
    public int vaoRef;

    public Uniform<Vector> 
    translation, baseColor;

    public static void main(String[] args)
    {
        Base test = new Test_Animate2();
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
                0.0f,  0.1f, 0.0f,
                0.1f, -0.1f, 0.0f,
                -0.1f, -0.1f, 0.0f  };
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
        // glClear(GL_COLOR_BUFFER_BIT);

        // select program to use when rendering
        glUseProgram( programRef );
        glBindVertexArray(vaoRef);

        // constant motion
        Vector position = new Vector( Math.cos(time), Math.sin(time), 0 );
        position.multiplyScalar(0.80);
        
        double hue = time % 1;
        Vector color = Vector.HSV_to_RGB(hue, 1, 1);
        
        // update the uniforms
        translation.data = position;
        baseColor.data = color;
        
        // draw the triangle
        translation.uploadData();
        baseColor.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );

    }
    
}

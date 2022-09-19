import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_Uniform extends Base
{
    public int programRef;
    public int vaoRef;

    public Uniform<Vector> 
    translation1, translation2,
    baseColor1, baseColor2;

    public static void main(String[] args)
    {
        Base test = new Test_Uniform();
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
                0.0f,  0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f  };
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        positionAttribute.associateVariable( programRef, "position" );

        // set up uniforms
        translation1 = new Uniform<Vector>("vec3", new Vector(-0.5f, 0.0f, 0.0f) );
        translation1.locateVariable( programRef, "translation" );
        translation2 = new Uniform<Vector>("vec3", new Vector(0.5f, 0.0f, 0.0f) );
        translation2.locateVariable( programRef, "translation" );
        baseColor1 = new Uniform<Vector>("vec3", new Vector(1.0f, 0.0f, 0.0f) );
        baseColor1.locateVariable( programRef, "baseColor" );
        baseColor2 = new Uniform<Vector>("vec3", new Vector(0.0f, 0.0f, 1.0f) );
        baseColor2.locateVariable( programRef, "baseColor" );

        // render settings (optional)
        glPointSize(20);
        glLineWidth(10);
    }

    public void update()
    {
        // select program to use when rendering
        glUseProgram( programRef );
        glBindVertexArray(vaoRef);
        
        // draw the first triangle
        translation1.uploadData();
        baseColor1.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );

        // draw the second triangle
        translation2.uploadData();
        baseColor2.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }

}

import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_Vector extends Base
{
    public int programRef1;
    public int programRef2;

    // each VAO stores associations/points
    //   from vertex buffers to shader program variables
    public int vaoRef1;
    public int vaoRef2;
    
    public static void main(String[] args)
    {
        Base test = new Test_Vector();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {       
        // initialize GPU programs
        
        programRef1 = OpenGLUtils.initFromFiles(
            "graphics/shaders/position.vert", 
            "graphics/shaders/yellow.frag" );

        programRef2 = OpenGLUtils.initFromFiles(
            "graphics/shaders/position.vert", 
            "graphics/shaders/violet.frag" );

        // initialize vertex array objects
        
        vaoRef1 = glGenVertexArrays();

        vaoRef2 = glGenVertexArrays();

        // initialize vector data

        // store results as vectors in a list; 
        //   convert to array of floats later
        ArrayList<Vector> vectorList = new ArrayList<Vector>();
        
        double tMin = -3.14;
        double tMax = 3.14;
        int numPoints = 20;
        double deltaT = (tMax - tMin) / numPoints;
        
        for (double t = tMin; t <= tMax; t += deltaT)
        {
            Vector v = new Vector( t , Math.sin(t), 0 );
            v.multiplyScalar(0.25);
            vectorList.add( v );
        }
        
        
        float[] positionData = Vector.flattenList( vectorList );
                 
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        
        glBindVertexArray( vaoRef1 );
        positionAttribute.associateVariable( programRef1, "position" );

        glBindVertexArray( vaoRef2 );
        positionAttribute.associateVariable( programRef2, "position" );

        // render settings (optional)

        // set point width and height
        glPointSize(8);
        
        glLineWidth(4);
    }

    public void update()
    {
        // select program, bind buffers, and render
        glUseProgram( programRef1 );
        glBindVertexArray( vaoRef1 );
        glDrawArrays(GL_LINE_STRIP, 0, 20);

        // select program, bind buffers, and render
        glUseProgram( programRef2 ); 
        glBindVertexArray( vaoRef2 );
        glDrawArrays(GL_POINTS, 0, 20);
}

}

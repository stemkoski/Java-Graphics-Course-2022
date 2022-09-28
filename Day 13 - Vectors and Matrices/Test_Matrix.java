import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_Matrix extends Base
{
    public static void main(String[] args)
    {
        Base test = new Test_Matrix();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {       
        System.out.println("Testing matrix class:");
        
        Matrix m = new Matrix(2,3);
        m.setValues(1,2,3,4,5,6);
        System.out.println(m);
    
        Matrix n = m.transpose();
        System.out.println(n);

        System.out.println(Matrix.multiply(m, n));
        System.out.println(Matrix.multiply(n, m));

        Matrix p = new Matrix(2,2);
        p.setValues(1,2,3,4);
        System.out.println(p);

        System.out.println("Testing Inverse Method");
        System.out.println(p.determinant());
        Matrix pInv = p.inverse();
        System.out.println(pInv);
        System.out.println( Matrix.multiply(p, pInv) );
        System.out.println( Matrix.multiply(pInv, p) );
    }

    public void update()
    {
        
    }

}

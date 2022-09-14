package graphics.math;
import java.util.List;

public class Vector
{
    public double[] values;

    // initialize zero vector with given size
    public Vector(int size)
    {
        values = new double[size];
    }

    // initialize with contents
    public Vector(double... v)
    {
        values = new double[v.length];
        for (int i = 0; i < v.length; i++)
            values[i] = v[i];
    }

    public String toString()
    {
        String s = "[";
        for (int i = 0; i < values.length; i++)
            s += String.format("%6.2f", values[i]);
        s += "]";
        return s;
    }

    // add values (v1, v2, v3, ...) to the components of this vector
    public void addValues(double... v)
    {
        for (int i = 0; i < v.length; i++)
            values[i] += v[i];
    }

    // add components of other vector to this vector
    public void addVector(Vector v)
    {
        for (int i = 0; i < v.values.length; i++)
            values[i] += v.values[i];
    }

    // multiply components of this vector by a scalar value s
    public void multiplyScalar(double s)
    {
        for (int i = 0; i < values.length; i++)
            values[i] *= s;
    }

    // convert a list of vectors to a list of floats (for GPU processing)
    public static float[] flattenList(List<Vector> vecList)
    { 
        int listSize = vecList.size();
        int vecSize  = vecList.get(0).values.length;
        float[] flattened = new float[listSize * vecSize];
        for (int vecNumber = 0; vecNumber < listSize; vecNumber++)
        {  
            Vector v = vecList.get(vecNumber);
            for (int i = 0; i < vecSize; i++)
                flattened[vecNumber * vecSize + i] = (float)v.values[i];
        }
        return flattened;
    }
}
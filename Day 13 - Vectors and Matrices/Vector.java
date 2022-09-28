package graphics.math;

public class Vector
{
    public double[] values;

    public Vector(int size)
    {
        values = new double[size];
    }
    
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

    public static double dot(Vector v, Vector w)
    {
        double c = 0;
        for (int i = 0; i < v.values.length; i++)
            c += v.values[i] * w.values[i];
        return c;
    }

    
    public static Vector HSV_to_RGB(Vector hsv)
    {
        return Vector.HSV_to_RGB(hsv.values[0], hsv.values[1], hsv.values[2]);
    }
    
    public static Vector HSV_to_RGB(double h, double s, double v)
    {
        double r=0, g=0, b=0, f=0, p=0, q=0, t=0;
        int i = (int)Math.floor(h * 6);
        f = h * 6 - i;
        p = v * (1 - s);
        q = v * (1 - f * s);
        t = v * (1 - (1 - f) * s);
        switch (i % 6) 
        {
            case 0: r = v; g = t; b = p; break;
            case 1: r = q; g = v; b = p; break;
            case 2: r = p; g = v; b = t; break;
            case 3: r = p; g = q; b = v; break;
            case 4: r = t; g = p; b = v; break;
            case 5: r = v; g = p; b = q; break;
        }
        return new Vector(r, g, b);
    }
}
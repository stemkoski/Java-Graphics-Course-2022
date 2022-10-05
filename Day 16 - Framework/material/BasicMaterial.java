package graphics.material;

import graphics.math.Vector;

public class BasicMaterial extends Material
{
    public BasicMaterial()
    {
        super(
            "graphics/shaders/BasicMaterial.vert",
            "graphics/shaders/BasicMaterial.frag"  );
        
        addUniform("vec3", "baseColor", new Vector(1,1,1) );
        addUniform("bool", "useVertexColors", 0);
        locateUniforms();
    }
}
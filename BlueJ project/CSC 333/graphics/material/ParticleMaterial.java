package graphics.material;

import graphics.math.Vector;
import graphics.core.Texture;

import static org.lwjgl.opengl.GL40.*;

import java.util.HashMap;
import graphics.core.OpenGLUtils;
import graphics.core.Uniform;
import graphics.core.RenderSetting;

public class ParticleMaterial extends Material
{
    public ParticleMaterial(Texture texture)
    {
        super(
            "graphics/shaders/particle.vert",
            "graphics/shaders/particle.frag"  );

        drawStyle = GL_TRIANGLES;
        
        addUniform("vec3", "baseColor", new Vector(1,1,1) );
        addUniform("sampler2D", "image", new Vector(texture.textureRef, 1));

        locateUniforms();

        addRenderSetting( "pointSize", 16 );
    }
}


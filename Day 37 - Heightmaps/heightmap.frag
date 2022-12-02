uniform sampler2D grass;
uniform sampler2D rock;
uniform sampler2D heightmap;

in vec2 UV;
out vec4 fragColor;

void main()
{
	vec4 data       = texture(heightmap, UV);
	float height    = data.r;
	vec4 grassColor = texture(grass, 20.0 * UV);
	vec4 rockColor  = texture(rock, 20.0 * UV);
	
	if (height < 0.2)
	    fragColor = grassColor;
	else
	    fragColor = rockColor;
}
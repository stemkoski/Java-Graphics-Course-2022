uniform sampler2D image;
uniform sampler2D noise;
in vec2 UV;
uniform float time;
out vec4 fragColor;

void main()
{
	vec2 uvShift = UV + vec2( -0.033, 0.07 ) * time;

	vec4 noiseValues = texture( noise, uvShift );

	vec2 uvNoise  = UV + 0.8 * noiseValues.rg;
	vec2 uvNoise2 = UV + 0.5 * noiseValues.gb;
 
	vec4 color  = texture( image, uvNoise  );
	vec4 color2 = texture( image, uvNoise2 ); 

	fragColor = 1.50 * (color + color2) / 2.0;
}



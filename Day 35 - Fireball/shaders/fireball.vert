uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

uniform sampler2D noise;
uniform float time;

in vec3 vertexPosition;
in vec2 vertexUV;
out vec2 UV;

void main()
{
	// distort position coordinates
	vec2 uvShift = vertexUV + vec2( 0.10, -0.13 ) * time;
	
	vec4 noiseValues = texture( noise, uvShift );
	vec3 xyzNoise = 0.4 * noiseValues.rgb;
	vec3 position = vertexPosition + xyzNoise;

	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
	UV = 4.0 * vertexUV;
}
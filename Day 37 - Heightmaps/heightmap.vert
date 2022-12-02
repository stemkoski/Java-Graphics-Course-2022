uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

uniform sampler2D heightmap;

in vec3 vertexPosition;
in vec2 vertexUV;
out vec2 UV;

void main()
{
	vec4 data = texture(heightmap, vertexUV);
	float height = data.r;
	vec3 position = vertexPosition + vec3(0, 0, 2.0 * height);

	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
	UV = vertexUV;
}
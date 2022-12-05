in vec3  particlePosition;        

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main()
{
	// rgbaColor = vec4( hsv_to_rgb(particleColor), particleOpacity );
	// alive = particleAlive;
	vec4 eyePosition = viewMatrix * modelMatrix * vec4(particlePosition, 1.0);
	gl_PointSize = 500 * 16 / length(eyePosition);
	gl_Position = projectionMatrix * eyePosition;
}

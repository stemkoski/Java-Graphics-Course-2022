uniform vec3 baseColor;

uniform sampler2D image;

void main()
{             
	vec4 imageColor = texture(image, gl_PointCoord);

	gl_FragColor = vec4(baseColor, 1.0) * imageColor;
}
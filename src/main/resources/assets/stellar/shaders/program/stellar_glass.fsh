//
//#version 150
//
//uniform sampler2D DiffuseSampler;
//uniform sampler2D MainDepthSampler;
////uniform sampler2D CutoutDepthSampler;
//
//uniform mat4 invViewMat;
//uniform mat4 invProjMat;
//uniform vec3 cameraPos;
//uniform float Red;
//uniform float Green;
//uniform float Blue;
//
//in vec2 texCoord;
//in vec2 oneTexel;
//out vec4 fragColor;
//
//void main() {
//    vec4 diffuseColor = texture(DiffuseSampler, texCoord);
//    vec3 color = vec3(0.7, 0, 1);
//
//    fragColor = diffuseColor * vec4(color, 0.6);
//
//}
//
//float thickness = 0.25;
//float lines = 1;
//float lineSpacing = 4;
//vec4 fillColor = vec4(0.45, 0.05, 0.75, 0.75);
//vec3 center = vec3(102, 68, 482);
//
//const float near = 1.0; // Near clip plane (prob shouldnt change)
//const float far = 100.0; // Far clip plane
//
//float linearizeDepth(float depth) {
//    return (2.0 * near) / (far + near - depth * (far - near));
//}
//
//void main() {
//    float mainDepth = texture(MainDepthSampler, texCoord).r;
//    float depth = 1 - linearizeDepth(mainDepth);
//
//    vec4 depthWorld = vec4(line, line,  line, 1.0);
//    fragColor = depthWorld;
//
////    fragColor = original;
//}

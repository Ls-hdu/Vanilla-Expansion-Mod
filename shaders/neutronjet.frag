uniform float u_time;
uniform vec4 u_color1;
uniform vec4 u_color2;
uniform float u_brightness;

varying vec4 v_color;
varying vec4 v_mix_color;
varying vec2 v_texCoords;

void main(){
    vec2 uv = v_texCoords;
    vec2 center = vec2(0.5);
    float dist = distance(uv, center);

    float glow = exp(-dist * 5.0) * 1.8;
    float pulse = 0.85 + 0.15 * sin(u_time * 4.0 + dist * 12.0);
    float core = exp(-dist * 18.0) * 2.5;
    float rim = exp(-abs(dist - 0.35) * 8.0) * 0.6;

    vec3 col = mix(u_color1.rgb, u_color2.rgb, dist * 1.2);
    col *= (1.0 + glow + core + rim) * pulse * u_brightness;
    col += vec3(0.6, 0.4, 0.8) * exp(-dist * 8.0) * 0.5;

    float twinkle = 0.85 + 0.15 * sin(u_time * 5.0 + uv.x * 30.0 + uv.y * 20.0);
    float alpha = smoothstep(0.9, 0.05, dist) * u_color1.a * twinkle;

    gl_FragColor = v_color * vec4(col, alpha);
}

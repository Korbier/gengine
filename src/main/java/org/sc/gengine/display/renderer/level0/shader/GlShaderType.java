package org.sc.gengine.display.renderer.level0.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

public enum GlShaderType {

	Fragment( GL20.GL_FRAGMENT_SHADER ),
	Vertex(   GL20.GL_VERTEX_SHADER ),
	Geometry( GL32.GL_GEOMETRY_SHADER );
	
	private int type = -1;
	private GlShaderType( int type ) {
		this.type = type;
	}
	
	public int type() {
		return this.type;
	}
	
}

package org.sc.gengine.display.renderer.level0.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

public class GlShader {

	private GlShaderType type   = null; 
	private int        id     = -1;
	private String     source = null;

	public GlShader( GlShaderType type, String source ) {
		this.type = type;
		setSource( source );
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public void load() {
		
		this.id = GL20.glCreateShader( this.type.type() );
		if ( this.id == 0 ) {
			System.err.println( "Error creating shader. Type: " + this.type.type() );
		}
		 
		GL20.glShaderSource( this.id, getSource() );
		GL20.glCompileShader( this.id );
		
		int status = GL20.glGetShaderi( this.id, GL20.GL_COMPILE_STATUS);
		if (status == GL11.GL_FALSE){
	        
	        String error=GL20.glGetShaderInfoLog(this.id);
	        
	        String ShaderTypeString = null;
	        switch( this.type.type() ) {
		        case GL20.GL_VERTEX_SHADER:   ShaderTypeString = "vertex";   break;
		        case GL32.GL_GEOMETRY_SHADER: ShaderTypeString = "geometry"; break;
		        case GL20.GL_FRAGMENT_SHADER: ShaderTypeString = "fragment"; break;
	        }
	        
	        System.err.println( "Compile failure in " + ShaderTypeString + " shader:\n" + error + "\n");
	    
		}
		
	}
	
	public void dispose() {
		GL20.glDeleteShader( this.id );
	}
	
}

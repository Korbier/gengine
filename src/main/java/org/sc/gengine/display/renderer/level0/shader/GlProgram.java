package org.sc.gengine.display.renderer.level0.shader;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class GlProgram {

	private int                  id       = -1;
	private List<GlShader>       shaders  = new ArrayList<GlShader>();
	private Map<String, Integer> uniforms = new HashMap<String, Integer>();
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer( 4 * 4 );
	
	public int getId() {
		return this.id;
	}
	
	public void addShader( GlShader shader ) {
		this.shaders.add(shader);
	}
	
	public void declareUniform( String uniform ) {
		this.uniforms.put( uniform, -1);
	}
		
	public void load() {
		
		this.id = GL20.glCreateProgram();
		
		if (this.id == 0) {
			System.err.println( "Could not create Shader" );
			return;
        }
		
		for ( GlShader shader : this.shaders ) {
			shader.load();
			GL20.glAttachShader( this.id, shader.getId() );
		}
		
		GL20.glLinkProgram( this.id );
        if (GL20.glGetProgrami(this.id, GL20.GL_LINK_STATUS) == 0) {
            System.err.println( "Error linking Shader code: " + GL20.glGetProgramInfoLog(this.id, 1024));
        }

        if ( !this.createUniforms() ) {
        	System.err.println( "Error getting uniforms from shader" );
        }
        
		for ( GlShader shader : this.shaders ) {
			GL20.glDetachShader( this.id, shader.getId() );
		}
		
		GL20.glValidateProgram( this.id );
		
		if ( GL20.glGetProgrami( this.id, GL20.GL_VALIDATE_STATUS ) == 0) {
            System.err.println("Warning validating Shader code: " + GL20.glGetProgramInfoLog( this.id, 1024) );
        }

	}
	
	public void unload() {
		
		for ( GlShader shader : this.shaders ) {
			shader.dispose();
		}
		
		unbind();
		GL20.glDeleteProgram( this.id );
		
	}
	
	public void bind() {
		GL20.glUseProgram( this.id );
	}
	
	public void unbind() {
		GL20.glUseProgram( 0 );
	}

	public void setUniform( String name, Matrix4f matrix) {
        matrix.get( GlProgram.matrixBuffer  );
        try {
        	GL20.glUniformMatrix4fv(this.uniforms.get( name ), false, GlProgram.matrixBuffer);
        } catch (Throwable t ){
        	System.err.println( "Failed uniform : " + name );
        	t.printStackTrace();
        }
	}
	
	public void setUniform( String name, Vector2f vector ) {
		int location = this.uniforms.get( name );
		GL20.glUniform2f( location, vector.x, vector.y );
	}

	public void setUniform( String name, Vector4f vector ) {
		int location = this.uniforms.get( name );
		GL20.glUniform4f( location, vector.x, vector.y, vector.z, vector.w );
	}
	
	public void setUniform( String name, float value ) {
		int location = this.uniforms.get( name );
		GL20.glUniform1f( location, value );		
	}
	
	private boolean createUniforms() {
		
		for ( String uniform : this.uniforms.keySet() ) {
			int uLocation = GL20.glGetUniformLocation( getId(), uniform);
			if ( uLocation < 0 ) {
				System.err.println( "Coudn't find uniform " + uniform );
				return false;
			} else {
				this.uniforms.put( uniform, uLocation);
			}
		}
		
		return true;
		
	}
}

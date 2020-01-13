package org.sc.gengine.display.renderer.level1;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.sc.gengine.display.renderer.level0.shader.GlProgram;
import org.sc.gengine.display.renderer.level0.shader.GlShader;
import org.sc.gengine.display.renderer.level0.shader.GlShaderType;
import org.sc.gengine.resource.shader.ShaderLoader;

public abstract class Material {

	public final static String UNIFORM_PROJECTION_MATRIX = "pMatrix";
	public final static String UNIFORM_VIEW_MATRIX       = "vMatrix";
	public final static String UNIFORM_WORLD_MATRIX      = "wMatrix";
	public final static String UNIFORM_TEXTURE           = "texture";
	
	private GlProgram program  = null;
	
	public Material() {
		this.program = new GlProgram();
		getProgram().addShader( new GlShader( GlShaderType.Vertex,   ShaderLoader.get().getShader( getVertexShaderFile() ).getSource() ) );
		getProgram().addShader( new GlShader( GlShaderType.Fragment, ShaderLoader.get().getShader( getFragmentShaderFile() ).getSource() ) );
		getProgram().declareUniform( Material.UNIFORM_PROJECTION_MATRIX );
		getProgram().declareUniform( Material.UNIFORM_VIEW_MATRIX );
		getProgram().declareUniform( Material.UNIFORM_WORLD_MATRIX );
		getProgram().declareUniform( Material.UNIFORM_TEXTURE );
	}
	
	protected abstract String getVertexShaderFile();
	protected abstract String getFragmentShaderFile();
	
	protected GlProgram getProgram() {
		return this.program;
	}
	
	public int getId() {
		return this.program.getId();
	}
	
	public void load() {
		this.program.load();
	}
	
	public void unload() {
		this.program.unload();
	}

	public void activate() {
		this.program.bind();
	}
	
	public void unactivate() {
		this.program.unbind();
	}
	
	public void setTransform( Matrix4f transform ) {
		this.program.setUniform( Material.UNIFORM_WORLD_MATRIX, transform );
	}

	public void setView( Matrix4f view ) {
		this.program.setUniform( Material.UNIFORM_VIEW_MATRIX, view );
	}
	
	public void setProjection( Matrix4f projection ) {
		this.program.setUniform( Material.UNIFORM_PROJECTION_MATRIX, projection );
	}
	
	public void setTexture( Vector2f count, Vector2f selection ) {
		this.program.setUniform( Material.UNIFORM_TEXTURE, new Vector4f( count.x, count.y, selection.x, selection.y ) );
	}

}

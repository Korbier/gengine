package org.sc.gengine.display.renderer.level1;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Renderer {
	
	private Matrix4f projection = null;
	private Matrix4f view       = null;
	
	private int currentModel    = -1;
	private int currentTexture  = -1;
	private int currentMaterial = -1;
	
	public void setProjection( Matrix4f projection ) {
		this.projection = projection;
	}
	
	public void setView( Matrix4f view ) {
		this.view = view;
	}
	
	public void render( Mesh mesh, Matrix4f transform, Vector2f textureSelection ) {
		
		activateModel( mesh.getModel() );
		activateTexture( mesh.getTexture() );
		activateMaterial( mesh.getMaterial() );
		
		mesh.getMaterial().setProjection( this.projection );
		mesh.getMaterial().setView( this.view );
		mesh.getMaterial().setTransform( transform );
		mesh.getMaterial().setTexture( mesh.getTexture().getCount(), textureSelection );

		mesh.getModel().render();
		
	}
	
	private void activateModel( Model model ) {
		
		if ( this.currentModel <= 0 || model.getId() != this.currentModel ) {
			this.currentModel = model.getId();
			model.activate();
		}
		
	}
	
	private void activateTexture( Texture texture ) {
		
		if ( this.currentTexture <= 0 || texture.getId() != this.currentTexture ) {
			this.currentTexture = texture.getId();
			texture.activate();
		}
				
	}
	
	private void activateMaterial( Material material ) {
		
		if ( this.currentMaterial <= 0 || material.getId() != this.currentMaterial ) {
			this.currentMaterial = material.getId();
			material.activate();
		}
				
	}
	
}

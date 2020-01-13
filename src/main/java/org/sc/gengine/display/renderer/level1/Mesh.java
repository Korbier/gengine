package org.sc.gengine.display.renderer.level1;

public class Mesh {

	private Texture  texture  = null;
	private Material material = null;
	private Model    model    = null;
	
	public Mesh() {
		configure( null, null, null );
	}
	
	public Mesh( Texture texture, Material material, Model model ) {
		configure( texture, material, model );
	}
	
	protected void configure( Texture texture, Material material, Model model ) {
		this.texture  = texture;
		this.material = material;
		this.model    = model;
	}
	
	public Texture getTexture() {
		return this.texture;
	}

	public Material getMaterial() {
		return this.material;
	}
	
	public Model getModel() {
		return this.model;
	}
	
}


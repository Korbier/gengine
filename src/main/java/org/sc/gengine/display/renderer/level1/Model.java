package org.sc.gengine.display.renderer.level1;

import org.sc.gengine.display.renderer.level0.GlVertexArrayObject;

public class Model {
	
	private GlVertexArrayObject vao = null; 
	
	public Model( Vertex [] vertices, int [] indices ) {
		this.vao = new GlVertexArrayObject();
		this.vao.setIndices( indices );
		this.vao.setPositions( linearizePositions( vertices ) );
		this.vao.setColors(    linearizeColors( vertices ) );
		this.vao.setTextures(  linearizeTextures( vertices ) );
	}
    
	public void setTexture( float ... textures ) {
		this.vao.setTextures( textures );
	}
	
	public void load() {
		this.vao.load();
	}
	
	public void unload() {
		this.vao.unload();
	}
	
	public void activate() {
		this.vao.bind();
	}
	
	public void unactivate() {
		this.vao.unbind();
	}

	public void render() {
		this.vao.render();
	}

	public int getId() {
		return this.vao.getId();
	}

    private float [] linearizeTextures( Vertex ... vertices ) {
    	float [] result = new float[vertices.length * 2];
    	for ( int i=0; i<vertices.length; i++) {
    		System.arraycopy(vertices[i].flattenTextures(), 0, result, i * 2, 2);
    	}
    	return result;
    }
    
    private float [] linearizeColors( Vertex ... vertices ) {
    	float [] result = new float[vertices.length * 4];
    	for ( int i=0; i<vertices.length; i++) {
    		System.arraycopy(vertices[i].flattenColors(), 0, result, i * 4, 4);
    	}
    	return result;
    }
    
	private float [] linearizePositions( Vertex ... vertices ) {
    	float [] result = new float[vertices.length * 3];
    	for ( int i=0; i<vertices.length; i++) {
    		System.arraycopy(vertices[i].flattenCoords(), 0, result, i * 3, 3);
    	}
    	return result;
	}

}

package org.sc.gengine.display.renderer.level1;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Vertex {

	private Vector3f coords  = new Vector3f();
	private Vector4f color   = new Vector4f();
	private Vector2f texture = new Vector2f();
	private Vector3f normale = new Vector3f();

	
	public Vertex( float x, float y, float z ) {
		getCoords().set(x, y, z);
	}
	
	public Vertex( Vector3f coord ) {
		getCoords().set( coord );
	}
	
	public Vertex( Vertex other ) {
		set( other );
	}
	
	public void set( Vertex other ) {
		getCoords().set( other.getCoords() );
		getColor().set( other.getColor() );
		getTexture().set( other.getTexture() );
		getNormale().set( other.getNormale() );
	}

	public Vector3f getCoords() {
		return this.coords;
	}
	
	public float [] flattenCoords() {
		return new float [] { getCoords().x, getCoords().y, getCoords().z };
	}
	
	public float [] flattenColors() {
		return new float [] { getColor().x, getColor().y, getColor().z, getColor().w };
	}
	
	public float [] flattenTextures() {
		return new float [] { getTexture().x, getTexture().y };
	}
	
	public Vector4f getColor() {
		return this.color;
	}
	
	public Vector2f getTexture() {
		return this.texture;
	}

	public Vector3f getNormale() {
		return this.normale;
	}
	
}

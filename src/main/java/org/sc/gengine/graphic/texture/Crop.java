package org.sc.gengine.graphic.texture;

import org.joml.Vector2i;

public class Crop {

	private Vector2i position = new Vector2i();
	private Vector2i size     = new Vector2i();
	
	public static Crop of( int x, int y, int w, int h ) {
		return of( new Vector2i(x, y), new Vector2i(w, h) );
	}
	
	public static Crop of( int w, int h ) {
		return of( new Vector2i(w, h) );
	}
	
	public static Crop of( Vector2i position, Vector2i size ) {
		return new Crop( position, size );
	}
	
	public static Crop of( Vector2i size ) {
		return new Crop( new Vector2i(0,0), size );
	}
	
	public static Crop join( Crop source, Crop inner ) {
	
		Vector2i position = new Vector2i();
		position.x = source.position.x + inner.position.x;
		position.y = source.position.y + inner.position.y;
		
		//on borne la taille a la limite du crop source
		Vector2i size = new Vector2i();
		size.x = Math.min(inner.size.x, source.position.x + source.size.x - inner.position.x );
		size.y = Math.min(inner.size.y, source.position.y + source.size.y - inner.position.y );
		
		return of( position, size );
		
	}
	
	private Crop( Vector2i position, Vector2i size ) {
		this.position = position;
		this.size     = size;
	}
	
	public Vector2i getPosition() {
		return this.position;
	}
	
	public Vector2i getSize() {
		return this.size;
	}
	
	public String toString() {
		return "Crop[x,y=" + this.position.x + "," + this.position.y + " w,h=" + this.size.x + "," + this.size.y + "]";  
	}
	
}

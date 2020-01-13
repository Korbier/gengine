package org.sc.gengine.display.renderer.level1;

import org.joml.Vector2f;
import org.sc.gengine.display.renderer.level0.texture.GlTexture;
import org.sc.gengine.resource.image.Image;
import org.sc.gengine.resource.image.ImageLoader;

public class Texture {

	private GlTexture gltexture = null;
	private Vector2f  count     = new Vector2f( 1.0f, 1.0f );
	
	public Texture( String filename ) {
		Image image = ImageLoader.get().getPNG( filename );
		this.gltexture = new GlTexture( image.toByteBuffer(), image.getWidth(), image.getHeight() );
	}
	
	public int getId() {
		return this.gltexture.getId();
	}
	
	public void load() {
		this.gltexture.load();
	}
	
	public void unload() {
		this.gltexture.unload();
	}
	
	public void activate() {
		this.gltexture.bind();
	}
	
	public void unactivate() {
		this.gltexture.unbind();
	}
	
	public Vector2f getCount() {
		return this.count;
	}
	
}

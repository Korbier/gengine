package org.sc.gengine.graphic.texture;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.sc.gengine.graphic.image.Image;
import org.sc.gengine.graphic.image.ImageFactory;
import org.sc.gengine.graphic.image.Type;
import org.sc.gengine.graphic.image.exception.ImageException;

public class Repository {

	private Path               root  = null;
	private Map<String, Image> cache = new HashMap<>();
	
	public Repository( Path root ) {
		this.root = root;
	}
	
	public Path getRoot() {
		return this.root;
	}
	
	public int size() {
		return this.cache.size();
	}
	
	public Texture get( String name ) throws ImageException {
		Image image = getImage( name );
		return new Texture( image, Crop.of( image.getSize() ) );
	}
	
	public Texture get( String name, Crop crop ) throws ImageException {
		return new Texture( getImage( name ), crop );
	}
	
	private Image getImage( String name ) throws ImageException {
		
		Image image = this.cache.get( name );
		
		if ( image == null ) {
			Path path = getRoot().resolve( name );
			image = ImageFactory.get().load( Type.PNG, path);
			this.cache.put( name, image );
		}
		
		return image;
		
	}
	
}

package org.sc.gengine.graphic.image;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.joml.Vector2i;
import org.sc.gengine.graphic.image.exception.ImageException;
import org.sc.gengine.graphic.image.exception.LoadFailureException;
import org.sc.gengine.graphic.image.exception.UnsupportedTypeException;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class ImageFactory {

	private static ImageFactory instance = new ImageFactory();
	private ImageFactory() {}
	
	public static ImageFactory get() {
		return instance;
	}
	
	public Image load( Type type, Path path ) throws ImageException {
		
		switch ( type ) {
			case PNG: return loadFromPng( path );
		}
		
		throw new UnsupportedTypeException( type );
		
	}
	
	private Image loadFromPng( Path path ) throws ImageException {
		
		try ( InputStream input = Files.newInputStream( path, StandardOpenOption.READ ) ) {
			
			PNGDecoder decoder = new PNGDecoder(input);
			Vector2i   size    = new Vector2i(decoder.getWidth(), decoder.getHeight());
			ByteBuffer buffer  = ByteBuffer.allocate( 4 * decoder.getWidth() * decoder.getHeight() );
			
			decoder.decode( buffer, decoder.getWidth() * 4, Format.RGBA );
			
			buffer.flip();
			
			return new Image( size, buffer );
			
		} catch (Exception ex) {
			throw new LoadFailureException( Type.PNG, path, ex );
		}
		
	}
	
}

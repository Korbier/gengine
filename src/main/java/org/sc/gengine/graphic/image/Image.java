package org.sc.gengine.graphic.image;

import java.nio.ByteBuffer;

import org.joml.Vector2i;

public class Image {

	private Vector2i   size = new Vector2i();
	private ByteBuffer data = null;
	
	Image( Vector2i size, ByteBuffer data ) {
		this.size = size;
		this.data = data;
	}
	
	public Vector2i getSize() {
		return this.size;
	}
	
	public ByteBuffer getData() {
		return this.data;
	}
	
}

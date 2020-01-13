package org.sc.gengine.display.renderer.level0.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class GlTexture {

	private int        id     = -1;
	
	private ByteBuffer buffer = null;
	private int        width  = 0;
	private int        height = 0;
	
	public GlTexture( ByteBuffer buffer, int width, int height ) {
		this.buffer = buffer;
		this.width  = width;
		this.height = height;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void load() {
		
		this.id = GL11.glGenTextures();
		

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width, this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, this.buffer);
		GL30.glGenerateMipmap( GL11.GL_TEXTURE_2D );
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		
	}
	
	public void unload() {
		GL11.glDeleteTextures( getId() );
	}
	
	public void bind() {
		GL13.glActiveTexture( GL13.GL_TEXTURE0 );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, getId() );
	}
	
	public void unbind() {
		GL11.glDeleteTextures( getId() );
	}
	
}

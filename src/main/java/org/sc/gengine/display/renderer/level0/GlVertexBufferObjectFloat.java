package org.sc.gengine.display.renderer.level0;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class GlVertexBufferObjectFloat implements GlObject {

	private int id         = -1;
	private int drawType   = -1;
	private int bufferType = -1;

	private float [] data = null;
	
	public GlVertexBufferObjectFloat( int bufferType, int drawType, float ... data ) {
		this.drawType   =  drawType;
		this.bufferType = bufferType;
		this.data       = data;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	public void load() {
		
		FloatBuffer fBuffer = null;
		
		try {
		
			this.id = glGenBuffers();
	        
			fBuffer = MemoryUtil.memAllocFloat( this.data.length );
			fBuffer.put( this.data ).flip();
			
			glBindBuffer( this.bufferType, getId() ); //GL_ELEMENT_ARRAY_BUFFER
			glBufferData( this.bufferType, fBuffer, this.drawType); //GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW

		} finally {
			if ( fBuffer != null ) {
                MemoryUtil.memFree( fBuffer );
            }
		}
		
	}
	
	public void unload() {
		
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers( getId() );

	}
	
	public void setData( float ... data ) {
		this.data = data;
	}
	
}

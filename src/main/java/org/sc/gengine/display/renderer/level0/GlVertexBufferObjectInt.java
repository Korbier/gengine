package org.sc.gengine.display.renderer.level0;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.IntBuffer;

import org.lwjgl.system.MemoryUtil;

public class GlVertexBufferObjectInt implements GlObject {

	private int id         = -1;
	private int drawType   = -1;
	private int bufferType = -1;

	private int [] data = null;
	
	public GlVertexBufferObjectInt( int bufferType, int drawType, int ... data ) {
		this.drawType   =  drawType;
		this.bufferType = bufferType;
		this.data       = data;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	public void load() {
		
		IntBuffer iBuffer = null;
		
		try {
		
			this.id = glGenBuffers();
	        
			iBuffer = MemoryUtil.memAllocInt(this.data.length);
			iBuffer.put( this.data ).flip();
			
			glBindBuffer( this.bufferType, getId() ); //GL_ELEMENT_ARRAY_BUFFER
			glBufferData( this.bufferType, iBuffer, this.drawType); //GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW

		} finally {
			if ( iBuffer != null ) {
                MemoryUtil.memFree( iBuffer );
            }
		}
		
	}
	
	public void unload() {
        glDeleteBuffers( getId() );
	}
	
}

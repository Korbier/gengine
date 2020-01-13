package org.sc.gengine.display.renderer.level0;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.HashMap;
import java.util.Map;

public class GlVertexArrayObject implements GlObject {

	public final static int ATTRIB_POSITION = 0;
	public final static int ATTRIB_COLOR    = 1;
	public final static int ATTRIB_TEXTURE  = 2;
	
	private int id = -1;
	
	private int                                     vertexCount = 0;
	private GlVertexBufferObjectInt                 indices     = null;
	private Map<Integer, GlVertexBufferObjectFloat> buffers     = new HashMap<>();
	
	@Override
	public int getId() {
		return this.id;
	}
	
	public void setPositions( float ... positions ) {
		this.buffers.put(
			GlVertexArrayObject.ATTRIB_POSITION, 
			new GlVertexBufferObjectFloat( GL_ARRAY_BUFFER, GL_STATIC_DRAW, positions ) 
		);
	}
	
	public void setColors( float ... colors ) {
		this.buffers.put(
			GlVertexArrayObject.ATTRIB_COLOR, 
			new GlVertexBufferObjectFloat( GL_ARRAY_BUFFER, GL_STATIC_DRAW, colors ) 
		);		
	}
	
	public void setTextures( float ... textures ) {
		this.buffers.put(
			GlVertexArrayObject.ATTRIB_TEXTURE, 
			new GlVertexBufferObjectFloat( GL_ARRAY_BUFFER, GL_STATIC_DRAW, textures ) 
		);		
	}
	
	public void setIndices( int ... indices ) {
        this.indices     = new GlVertexBufferObjectInt( GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW, indices );
        this.vertexCount = indices.length;
	}
	
	public void load() {
		
		this.id = glGenVertexArrays();
		glBindVertexArray( this.id );
		
		this.buffers.get( GlVertexArrayObject.ATTRIB_POSITION ).load();
        glVertexAttribPointer(GlVertexArrayObject.ATTRIB_POSITION, 3, GL_FLOAT, false, 0, 0);

		this.buffers.get( GlVertexArrayObject.ATTRIB_COLOR ).load();
        glVertexAttribPointer(GlVertexArrayObject.ATTRIB_COLOR, 4, GL_FLOAT, false, 0, 0);

		this.buffers.get( GlVertexArrayObject.ATTRIB_TEXTURE ).load();
        glVertexAttribPointer(GlVertexArrayObject.ATTRIB_TEXTURE, 2, GL_FLOAT, false, 0, 0);
        
        this.indices.load();

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        
	}
	
	public void bind() {
		
		glBindVertexArray( getId() );
		glEnableVertexAttribArray( GlVertexArrayObject.ATTRIB_POSITION );
	    glEnableVertexAttribArray( GlVertexArrayObject.ATTRIB_COLOR );
	    glEnableVertexAttribArray( GlVertexArrayObject.ATTRIB_TEXTURE );
	    
	}
	
	public void unbind() {
        glDisableVertexAttribArray( GlVertexArrayObject.ATTRIB_POSITION );
        glDisableVertexAttribArray( GlVertexArrayObject.ATTRIB_COLOR );
        glDisableVertexAttribArray( GlVertexArrayObject.ATTRIB_TEXTURE );
        glBindVertexArray(0);
	}
	
	public void render() {
        glDrawElements(GL_TRIANGLES, this.vertexCount, GL_UNSIGNED_INT, 0);
	}
	
	public void unload() {
		
		this.indices.unload();
		this.buffers.get( GlVertexArrayObject.ATTRIB_COLOR ).unload();
		this.buffers.get( GlVertexArrayObject.ATTRIB_POSITION ).unload();
		this.buffers.get( GlVertexArrayObject.ATTRIB_TEXTURE ).unload();
		
        glBindVertexArray(0);
        glDeleteVertexArrays( getId() );
	
	}
	
}

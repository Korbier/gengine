package org.sc.gengine.graphic.animation;

import java.util.ArrayList;
import java.util.List;

import org.sc.gengine.graphic.texture.Texture;

public class Animation {

	private int           interval = 100;
	private int           cumul    = 0;
	
	private boolean       playing     = false;
	private boolean       loopEnabled = true;
	
	private int           current  = 0;
	private int           step     = 1;
	private List<Texture> frames   = new ArrayList<>();

	public void rewind() {
		this.current = 0;
	}
	
	public void pause() {
		this.playing = false;
	}
	
	public void play() {
		this.playing = true;
	}
	
	public void previous() {
		
		this.current -= this.step;
		
		if ( this.current < 0 ) {
			
			this.current = size() - 1;
			
			if ( !isLoopEnabled() ) {
				pause();
			}
			
		}
		
	}
	
	public void next() {
		
		this.current += this.step;
		
		if ( this.current >= size() )  {
			
			rewind();
			
			if ( !isLoopEnabled() ) {
				pause();
			}
			
		}
		
	}
	
	public void invert() {
		this.step = -this.step;
	}
	
	public boolean isPlaying() {
		return this.playing;
	}
	
	public boolean isLoopEnabled() {
		return this.loopEnabled;
	}
	
	public int size() {
		return this.frames.size();
	}
	
	void setLoopEnabled( boolean enabled ) {
		this.loopEnabled = enabled;
	}
	
	void setInterval( int interval ) {
		this.interval = interval;
	}
	
	void addFrame( Texture texture ) {
		this.frames.add( texture );
	}
	
	public void update( float tpf ) {
		
		if ( isPlaying() ) {
			this.cumul += tpf;
			if ( this.cumul > this.interval ) {
				this.next();
				this.cumul = 0;
			}
		}
		
	}
	
	
	public Texture frame() {
		return this.frames.get( this.current );
	}
	
}

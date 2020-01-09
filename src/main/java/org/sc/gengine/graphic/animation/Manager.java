package org.sc.gengine.graphic.animation;

import java.util.HashMap;
import java.util.Map;

import org.sc.gengine.graphic.texture.Texture;

public class Manager {

	private Selector                 selector   = null;
	private Map<Selector, Animation> animations = new HashMap<>();
	
	public void add( Selector selector, Animation animation ) {
		this.animations.put( selector, animation );
	}
	
	public void select( Selector selector ) {
		current().rewind();
		this.selector = selector;
	}
	
	public Animation current() {
		return this.animations.get( this.selector );
	}
	
	public void update( float tpf ) {
		current().update( tpf );
	}
	
	public Texture frame() {
		return current().frame();
	}
	
}

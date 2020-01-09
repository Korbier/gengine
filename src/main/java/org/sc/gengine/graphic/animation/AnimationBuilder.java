package org.sc.gengine.graphic.animation;

import org.sc.gengine.graphic.texture.Texture;

public class AnimationBuilder {

	private Animation animation = null;
	
	public AnimationBuilder() {
		this.animation = new Animation();
	}
	
	public AnimationBuilder frame( Texture texture ) { 
		this.animation.addFrame( texture );
		return this;
	}
	
	public AnimationBuilder frames( Texture spritesheet, int cols, int rows ) {
		
		int sheetWidth  = spritesheet.getCrop().getSize().x;
		int sheetHeight = spritesheet.getCrop().getSize().y;
		
		int colWidth  = sheetWidth  / cols;
		int rowHeight = sheetHeight / rows;
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				this.animation.addFrame( spritesheet.crop( j * colWidth, i * rowHeight, colWidth, rowHeight ) );
			}			
		}
		
		return this;
	}
	
	public AnimationBuilder interval( int interval ) {
		this.animation.setInterval( interval );
		return this;
	}
	
	public Animation build() {
		return this.animation;
	}
	
}

package org.sc.gengine.graphic.texture;

import org.sc.gengine.graphic.image.Image;

public class Texture {

	private Image image = null; //Pointeur vers l'image contenant la texture a afficher
	private Crop  crop  = null; //Partie de l'image a utiliser pour la texture
	
	Texture( Image image, Crop crop ) {
		this.image = image;
		this.crop  = crop;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public Crop getCrop() {
		return this.crop;
	}
	
	public Texture crop( Crop crop ) {
		return new Texture( getImage(), Crop.join( getCrop(), crop) );
	}

	public Texture crop( int x, int y, int w, int h ) {
		return new Texture( getImage(), Crop.join( getCrop(), Crop.of( x, y, w, h ) ) );
	}

	public Texture crop( int w, int h ) {
		return new Texture( getImage(), Crop.join( getCrop(), Crop.of( w, h ) ) );
	}
}

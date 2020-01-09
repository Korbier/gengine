package org.sc.gengine;

import java.nio.file.Paths;

import org.sc.gengine.graphic.animation.Animation;
import org.sc.gengine.graphic.animation.AnimationBuilder;
import org.sc.gengine.graphic.texture.Crop;
import org.sc.gengine.graphic.texture.Repository;
import org.sc.gengine.graphic.texture.Texture;

public class Bootstrap {

	public static void main(String[] args) {

		Repository repository = new Repository( Paths.get( "src\\main\\resources" ) );
		
		try {
			
			Texture t1 = repository.get( "sprite.png" );
			Texture t2 = repository.get( "sprite.png", Crop.of( 100, 100, 100, 100 ) );
			Texture t3 = t2.crop( Crop.of( 10, 10 ) );
			
			System.out.println( repository.size() );
			System.out.println( "t1 = " + t1.getCrop() );
			System.out.println( "t2 = " + t2.getCrop() );
			System.out.println( "t3 = " + t3.getCrop() );
		
			
			Texture anime1Texture = repository.get( "spritesheet.png" ).crop( 0, 0,  384, 64 );
			Texture anime2Texture = repository.get( "spritesheet.png" ).crop( 0, 32, 384, 32 );
			
			Animation animation1 = new AnimationBuilder().frames( anime1Texture, 12, 2 ).build();
			Animation animation2 = new AnimationBuilder().frames( anime2Texture, 12, 1 ).build();

			animation1.play();
			
			for ( int i = 0; i < 20; i++) {
				animation1.update( 101.f );
				System.out.println( animation1.frame().getCrop() );
			}
			
			System.out.println("stop");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
}

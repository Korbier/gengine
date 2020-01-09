package org.sc.gengine.state;

import org.sc.gengine.input.InputHandler;

public abstract interface Gamestate {

	public void setup();
	public void cleanup();

	public void update( GameStateStatus status, float interval );
	public void input ( GameStateStatus status, InputHandler inputHandler, float tpf );
	public void render( GameStateStatus status, float tpf );

}

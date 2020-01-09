package org.sc.gengine.state;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.sc.gengine.input.InputHandler;

public class GameStateManager {

	private Deque<Gamestate> deque = new ConcurrentLinkedDeque<Gamestate>();

	public void push( Gamestate gamestate ) {
		this.deque.push( gamestate );
		gamestate.setup();
	}

	public void replace( Gamestate gamestate ) {

		if ( !this.deque.isEmpty() ) {
			pop();
		}

		push( gamestate );

	}

	public void pop() {
		this.deque.pop().cleanup();
	}

	public Gamestate peek() {
		return this.deque.peek();
	}

	public void update( float interval ) {
		Iterator<Gamestate> gStates = this.deque.descendingIterator();
		while ( gStates.hasNext() ) {
			Gamestate gState = gStates.next();
			if ( this.deque.getFirst() == gState ) {
				gState.update( GameStateStatus.ACTIVE, interval );
			} else {
				gState.update( GameStateStatus.IDLE, interval );
			}
		}
	}

	public void input ( InputHandler inputHandler, float interval ) {
		Iterator<Gamestate> gStates = this.deque.descendingIterator();
		while ( gStates.hasNext() ) {
			Gamestate gState = gStates.next();
			if ( this.deque.getFirst() == gState ) {
				gState.input( GameStateStatus.ACTIVE, inputHandler, interval );
			} else {
				gState.input( GameStateStatus.IDLE, inputHandler, interval );
			}
		}
	}

	public void render( float interval ) {
		Iterator<Gamestate> gStates = this.deque.descendingIterator();
		while ( gStates.hasNext() ) {
			Gamestate gState = gStates.next();
			if ( this.deque.getFirst() == gState ) {
				gState.render( GameStateStatus.ACTIVE, interval );
			} else {
				gState.render( GameStateStatus.IDLE, interval );
			}
		}
	}
	
}

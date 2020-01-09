package org.sc.gengine.input;

import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class InputHandler extends GLFWKeyCallback {

	private long windowHandle = -1;

	private Map<Integer, KeyState>   keyStates   = new HashMap<Integer, KeyState>();
	private Map<Integer, Boolean>    alreadySent = new HashMap<Integer, Boolean>();
	
	private boolean inWindow     = true;
	
	public static boolean[] keys = new boolean[65536];
	private DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
	private DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
	
	private double scrollXOffset = 0.0d;
	private double scrollYOffset = 0.0d;
	
	public void initialise( long windowHandle ) {
		
		this.windowHandle = windowHandle;
		
		org.lwjgl.glfw.GLFW.glfwSetKeyCallback( this.windowHandle, this);
		
		org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback(this.windowHandle, new GLFWCursorEnterCallback() {
			@Override
			public void invoke(long window, boolean entered) {
				inWindow = entered;				
			}
		});
		
		org.lwjgl.glfw.GLFW.glfwSetScrollCallback( this.windowHandle, new GLFWScrollCallback() {
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				InputHandler.this.scrollXOffset = arg1;
				InputHandler.this.scrollYOffset = arg2;
			}
		} );
		
		org.lwjgl.glfw.GLFW.glfwSetInputMode(this.windowHandle, org.lwjgl.glfw.GLFW.GLFW_CURSOR, org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED);
	}
	
	public long getWindowHandle() {
		return this.windowHandle;
	}
	
	public void update( float tpf ) {
		GLFW.glfwPollEvents();
		GLFW.glfwGetCursorPos(this.windowHandle, b1, b2);
	}
	
	public boolean isInWindow() {
		return this.inWindow;
	}
	
	public double getMouseX() {
		return b1.get( 0 );
	}

	public double getMouseY() {
		return b2.get( 0 );
	}
	
	public double getScrollXOffset() {
		return this.scrollXOffset;
	}

	public double getScrollYOffset() {
		return this.scrollYOffset;
	}
	
	public boolean isKeyPressed(int key, boolean repeat) {
		
		boolean result = areKeyPressed(repeat, key);
		if ( !result ) return result;
		
		if ( !repeat ) {
			Boolean _alreadySent = alreadySent.get( key );
			if ( _alreadySent == null ) _alreadySent = Boolean.FALSE;
			alreadySent.put(key, Boolean.TRUE);
			return !_alreadySent.booleanValue();
		}
		
		return true;
		
	}
	
	public boolean areKeyPressed(boolean repeat, int ... keys) {
		
		//Etat de la touche
		KeyState [] kState = new KeyState[ keys.length ];//  this.keyStates.get( key );
		boolean _pressed = true;
		
		for ( int i=0; i<keys.length; i++) {
			
			int key = keys[i];
			
			kState[i] = this.keyStates.get( key );
			if ( kState[i] == null ) return false;
			
			boolean p = this.keyStates.get( key ).pressed;
			_pressed &= p;
			
			if ( !p ) alreadySent.put(key, Boolean.FALSE);
			
		}

		//On reset l'etat d'envoi de l'appui de la touche
		if ( !_pressed ) {
			return false;
		}
		
		return true;
			
	}

	public boolean isMouseButtonPressed(int buttonIndex) {
		return GLFW.glfwGetMouseButton( this.windowHandle, buttonIndex ) == 1;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		
		KeyState state = this.keyStates.get( key );
		
		if ( state == null ) {
			state = new KeyState();
			this.keyStates.put(key, state);
		}
		
		if ( action != org.lwjgl.glfw.GLFW.GLFW_RELEASE ) {
			state.pressed = true;
		} else {
			state.pressed = false;
		}
		
	}
	
	public static class KeyState {
		public boolean pressed  = false;
	}
	
}
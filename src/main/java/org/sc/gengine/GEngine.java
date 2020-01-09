package org.sc.gengine;

import org.sc.gengine.utils.Timer;
import org.sc.gengine.utils.message.IMessage;
import org.sc.gengine.utils.message.IMessageListener;
import org.sc.gengine.utils.message.MessageManager;
import org.sc.gengine.utils.message.SystemMessages;
import org.sc.gengine.state.GameStateManager;
import org.sc.gengine.display.window.Window;
import org.sc.gengine.display.window.WindowContext;
//import org.sc.ge8.graphic.sprite.renderer.IRenderer;
import org.sc.gengine.input.InputHandler;

public class GEngine implements Runnable, IMessageListener {
	
	public static final int TARGET_FPS = 150; //75;
    public static final int TARGET_UPS = 30;

    private final Window      window;
    private final InputHandler input;
    
    //private final IRenderer renderer;
    
    private final Thread  gameLoopThread;
    private final Timer   timer;
    
    private boolean running = false;

    private GameStateManager gameStateManager = null;	

    public GEngine( WindowContext dContext, /*IRenderer renderer,*/ GameStateManager gameStateManager ) throws Exception {
       
    	this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
    	this.timer          = new Timer();
       
        this.window        = new Window( dContext );
        this.input          = new InputHandler();
        //this.renderer       = renderer;
       
        this.gameStateManager = gameStateManager;
       
    }

    public void start() {
        String osName = System.getProperty("os.name");
        if ( osName.contains("Mac") ) {
            gameLoopThread.run();
        } else {
            gameLoopThread.start();
        }
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }

    public void interrupt() {
    	this.running = false;
    }
    
    protected void init() throws Exception {
        
    	MessageManager.get().addMessageListener( this );
    	
    	this.window.initialize();
        this.input.initialise( this.window.getWindowHandle() );
        
        this.timer.initialize();
       // this.renderer.initialize();
        
        
    }

    protected void gameLoop() {
       
    	float elapsedTime;
        float accumulator = 0f;
        float interval    = 1f / TARGET_UPS;

        float fpsCounterTimer = 0;
        float fpsCounter      = 0;
        
        this.running = true;
        while ( this.running && !this.window.shouldClose() ) {
        
        	elapsedTime = this.timer.getElapsedTime();
            accumulator += elapsedTime;

            input( elapsedTime );

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();
            fpsCounter++;
            
            fpsCounterTimer += elapsedTime;
            if ( fpsCounterTimer >= 1 ) {
            	System.out.println("Fps : " + fpsCounter );
            	fpsCounter = 0;
            	fpsCounterTimer = 0;
            }

            if ( !this.window.getContext().isvSync() ) {
                sync();
            }
            
        }
    }

    protected void cleanup() {
    	//this.renderer.dispose();
    	//this.gameStateManager.dispose();       
    }
    
    private void sync() {
        float  loopSlot = 1f / TARGET_FPS;
        double endTime  = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {}
        }
    }

    protected void input( float interval ) {
    	this.input.update( interval );
    	this.gameStateManager.input( this.input, interval );
    }

    protected void update( float interval ) {
    	this.gameStateManager.update( interval );
    }

    protected void render() {
    //	this.gameStateManager.render( this.display, this.renderer ); 
        this.window.update();
    }

	@Override
	public void onMessageReceived(Object source, IMessage message) {
		
		if ( message == SystemMessages.REQUEST_SHUTDOWN ) {
			 interrupt();
		}
		
	}

}

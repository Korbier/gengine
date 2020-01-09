package org.sc.gengine.utils.message;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
	
	public  static MessageManager get() { return _instance; }
	private static MessageManager _instance = new MessageManager();
	private MessageManager() {}
	
	private List<IMessageListener> _listeners = new ArrayList<IMessageListener>();
	
	public void addMessageListener( IMessageListener listener ) {
		this._listeners.add( listener );
	}
	
	public void removeMessageListener( IMessageListener listener ) {
		this._listeners.remove( listener );
	}
	
	public void fireMessage( Object source, IMessage message ) {
		for ( IMessageListener listener : _listeners ) {
			listener.onMessageReceived(source, message);
		}
	}
	
}

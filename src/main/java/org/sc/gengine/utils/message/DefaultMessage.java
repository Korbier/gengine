package org.sc.gengine.utils.message;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessage implements IMessage {

	private String _id = null;
	private Map<String, Object> _parameters = new HashMap<String, Object>();
	
	public DefaultMessage(String id, String ... parameters ) {
		this._id = id;
		for ( String parameter : parameters ) {
			this._parameters.put( parameter, null);
		}
	}
	
	@Override
	public String getId() {
		return this._id;
	}

	@Override
	public Object getParameter(String id) {
		return this._parameters.get( id );
	}
	
	public DefaultMessage setParameter(String id, Object value) {
		if ( this._parameters.containsKey( id ) ) this._parameters.put( id, value);
		return this;
	}

}

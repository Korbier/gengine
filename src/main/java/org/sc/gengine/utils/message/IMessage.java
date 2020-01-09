package org.sc.gengine.utils.message;

public interface IMessage {

	public String getId();
	public IMessage setParameter( String parameterId, Object value);
	public Object   getParameter( String parameterId );
	
}

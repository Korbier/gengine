package org.sc.gengine.graphic.image.exception;

import org.sc.gengine.graphic.image.Type;

public class UnsupportedTypeException extends ImageException {

	private static final long serialVersionUID = -8319941483583390488L;

	public UnsupportedTypeException(Type type) {
		super( "Unsupported type : " + type );
	}

}

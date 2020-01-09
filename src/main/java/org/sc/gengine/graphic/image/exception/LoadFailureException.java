package org.sc.gengine.graphic.image.exception;

import java.nio.file.Path;

import org.sc.gengine.graphic.image.Type;

public class LoadFailureException extends ImageException {

	private static final long serialVersionUID = -967647701373643099L;

	public LoadFailureException(Type type, Path path, Throwable cause) {
		super( "Failed to load image " + path.toString() + " of type " + type, cause );
	}

}

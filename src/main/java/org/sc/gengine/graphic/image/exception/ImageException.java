package org.sc.gengine.graphic.image.exception;


public abstract class ImageException extends Exception {

	private static final long serialVersionUID = -7848728232268599048L;

	public ImageException() {
		super();
	}

	public ImageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ImageException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageException(String message) {
		super(message);
	}

	public ImageException(Throwable cause) {
		super(cause);
	}

}

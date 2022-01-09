package es.iestetuan.dam2.exception;

public class Renova2CoopException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1073558969595263909L;
	public final static long NO_EXCEPCION=0;
	public final static long EXCEPCION_CREAR=1;
	public final static long EXCEPCION_ACTUALIZAR=1;
	public final static long EXCEPCION_BORRAR=1;
	public final static long EXCEPCION_CONSULTAR=1;
	
	private long codigoError; 


	public Renova2CoopException(long  codigoError, Exception excepcion){
		super(excepcion);
		this.codigoError=codigoError;
	}

	public long getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(long codigoError) {
		this.codigoError = codigoError;
	}
}

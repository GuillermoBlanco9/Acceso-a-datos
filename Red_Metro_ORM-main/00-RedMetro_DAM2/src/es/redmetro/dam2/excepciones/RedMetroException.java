package es.redmetro.dam2.excepciones;

public class RedMetroException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public static final String ERROR_ALTA="001";
	public static final String ERROR_MODIFICACION="002";
	public static final String ERROR_BAJA="003";
	public static final String ERROR_CONSULTA="004";
	private String codigoError;
	
	public RedMetroException(String codigoError, Exception e){
		super(e);
		setCodigoError(codigoError);
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

}

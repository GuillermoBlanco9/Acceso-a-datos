package es.iestetuan.dam2.vo;

public class Contrato {
	private long codigoContrato;
	private Socio socio;
	private Servicio servicio;
	private boolean indicadorPagoMensual;

	
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public long getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(long codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public boolean isIndicadorPagoMensual() {
		return indicadorPagoMensual;
	}
	public void setIndicadorPagoMensual(boolean indicadorPagoMensual) {
		this.indicadorPagoMensual = indicadorPagoMensual;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}

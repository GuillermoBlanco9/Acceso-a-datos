package es.redmetro.dam2.vo;

import java.util.Date;

public class Tren {
	
	private int codigoTren;
	private String modelo;
	private Date anyoIncorporacion;
	private String empresaConstructora;
	private Linea linea;
	private Cochera cochera;
	
	public Tren() {
		
	}
	
	public Tren(int codigoTren, String modelo, Date anyoIncorporacion, String empresaConstructora, Linea linea, Cochera cochera) {
		this.codigoTren=codigoTren;
		this.modelo=modelo;
		this.anyoIncorporacion=anyoIncorporacion;
		this.empresaConstructora=empresaConstructora;
		this.linea=linea;
		this.cochera=cochera;
	}
	
	public String getEmpresaConstructora() {
		return empresaConstructora;
	}
	public void setEmpresaConstructora(String empresaConstructora) {
		this.empresaConstructora = empresaConstructora;
	}
	public int getCodigoTren() {
		return codigoTren;
	}
	public void setCodigoTren(int codigoTren) {
		this.codigoTren = codigoTren;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getAnyoIncorporacion() {
		return anyoIncorporacion;
	}
	public void setAnyoIncorporacion(Date anyoIncorporacion) {
		this.anyoIncorporacion = anyoIncorporacion;
	}
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Cochera getCochera() {
		return cochera;
	}
	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}
	
	public String toString() {
		return "Tren [codigoTren=" + codigoTren + ", modelo=" + modelo + ", anyoIncorporacion=" + anyoIncorporacion
				+ ", empresaConstructora=" + empresaConstructora + ", linea=" + linea + ", cochera=" + cochera + "]";
	}
}

package es.iestetuan.dam2.vo;

import java.util.List;

public class Servicio {
	private long codigoServicio;
	private List<Contrato> contratos;
	private String descripcion;
	private float precio;
	
	
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public long getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}

package es.redmetro.dam2.vo;

import java.util.List;

public class Estacion {
	
	private int codigoEstacion;
	private String nombre;
	private String direccion;
	private List<Linea> lineas;
	
	public List<Linea> getLineas() {
		return lineas;
	}
	public void setLineas(List<Linea> listaLineas) {
		this.lineas = listaLineas;
	}
	public int getCodigoEstacion() {
		return codigoEstacion;
	}
	public void setCodigoEstacion(int codigoEstacion) {
		this.codigoEstacion = codigoEstacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Estacion [codigoEstacion=" + codigoEstacion + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", lineas=" + lineas + "]";
	}
}

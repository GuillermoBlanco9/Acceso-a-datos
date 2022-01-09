package es.redmetro.dam2.vo;

public class Acceso {
	private int codigoAcceso;
	private String nombre;
	private int tieneAccesoDiscapacidad;
	private Estacion estacion;
	
	public Acceso() {
		
	}
	
	public Acceso(int codigoAcceso, String nombre, int tieneAccesoDiscapacidad, Estacion estacion) {
		this.codigoAcceso=codigoAcceso;
		this.nombre=nombre;
		this.tieneAccesoDiscapacidad=tieneAccesoDiscapacidad;
		this.estacion=estacion;
	}
	
	public int getCodigoAcceso() {
		return codigoAcceso;
	}
	public void setCodigoAcceso(int codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int isTieneAccesoDiscapacidad() {
		return tieneAccesoDiscapacidad;
	}
	public void setTieneAccesoDiscapacidad(int tieneAccesoDiscapacidad) {
		this.tieneAccesoDiscapacidad = tieneAccesoDiscapacidad;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
}

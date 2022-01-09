package es.redmetro.dam2.vo;

public class Acceso {
	private int codigoAcceso;
	private String nombre;
	private int accesosDiscapacitados;
	private Estacion estacion;
	
	public Acceso() {
		
	}
	
	public Acceso(int codigoAcceso, String nombre, int numEntradasDiscapacitados, Estacion estacion) {
		this.codigoAcceso=codigoAcceso;
		this.nombre=nombre;
		this.accesosDiscapacitados=numEntradasDiscapacitados;
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
		return accesosDiscapacitados;
	}
	public void setTieneAccesoDiscapacidad(int tieneAccesoDiscapacidad) {
		this.accesosDiscapacitados = tieneAccesoDiscapacidad;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	@Override
	public String toString() {
		return "Acceso [codigoAcceso=" + codigoAcceso + ", nombre=" + nombre + ", accesosDiscapacitados="
				+ accesosDiscapacitados + ", estacion=" + estacion + "]";
	}
}

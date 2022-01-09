package es.redmetro.dam2.vo;

import java.util.List;
import java.util.Set;

public class Linea {

	private int codigoLinea;
	private String nombreCorto;
	private String nombreLargo;
	private Set<Estacion> estaciones;
	private Color color;
	private float kilometros;
	
	public int getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(int codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	public Set<Estacion> getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(Set<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public float getKilometros() {
		return kilometros;
	}
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}
	
	@Override
	public String toString() {
		return "Linea [codigoLinea=" + codigoLinea + ", nombreCorto=" + nombreCorto + ", nombreLargo=" + nombreLargo
				+ ", estaciones=" + estaciones + ", color=" + color + ", kilometros=" + kilometros + "]";
	}
}

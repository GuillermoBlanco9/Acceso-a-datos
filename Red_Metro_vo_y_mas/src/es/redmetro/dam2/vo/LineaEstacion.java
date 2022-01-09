package es.redmetro.dam2.vo;

public class LineaEstacion {
	private Linea linea;
	private Estacion estacion;
	private int orden;
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int ordenM) {
		this.orden = ordenM;
	}
	@Override
	public String toString() {
		return "LineaEstacion [linea=" + linea + ", estacion=" + estacion + ", orden=" + orden + "]";
	}
	
}

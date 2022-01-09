package es.iestetuan.dam2.vo;

public class PersonajeInspirador {
	private long codigoPersonaje;
	private GrupoTrabajo grupoTrabajo;
	private String nombre;
	private boolean indicadorPersonajeReal;

	
	public long getCodigoPersonaje() {
		return codigoPersonaje;
	}
	public void setCodigoPersonaje(long codigoPersonaje) {
		this.codigoPersonaje = codigoPersonaje;
	}
	public GrupoTrabajo getGrupoTrabajo() {
		return grupoTrabajo;
	}
	public void setGrupoTrabajo(GrupoTrabajo grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
	}	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isIndicadorPersonajeReal() {
		return indicadorPersonajeReal;
	}
	public void setIndicadorPersonajeReal(boolean indicadorPersonajeReal) {
		this.indicadorPersonajeReal = indicadorPersonajeReal;
	}

}

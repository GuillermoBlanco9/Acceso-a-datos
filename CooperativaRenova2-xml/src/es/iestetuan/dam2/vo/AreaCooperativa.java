package es.iestetuan.dam2.vo;

import java.util.Set;

public class AreaCooperativa {
	private long codigoAreaCooperativa;
	private Set<GrupoTrabajo> gruposTrabajo;
	private String descripcion;	
	
	public Set<GrupoTrabajo> getGruposTrabajo() {
		return gruposTrabajo;
	}
	public void setGruposTrabajo(Set<GrupoTrabajo> gruposTrabajo) {
		this.gruposTrabajo = gruposTrabajo;
	}
	public long getCodigoAreaCooperativa() {
		return codigoAreaCooperativa;
	}
	public void setCodigoAreaCooperativa(long codigoAreaCooperativa) {
		this.codigoAreaCooperativa = codigoAreaCooperativa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}

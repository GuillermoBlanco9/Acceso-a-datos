package es.iestetuan.dam2.vo;

import java.util.List;
import java.util.Set;

public class GrupoTrabajo {
	private long codigoGrupoTrabajo;
	private Set<AreaCooperativa> areasCooperativa;
	private PersonajeInspirador personajeInspirador;
	private List<SocioGrupoTrabajo> sociosGrupoTrabajo;
	private String descripcionCorta;
	private String descripcionLarga;
	
	
	
	public PersonajeInspirador getPersonajeInspirador() {
		return personajeInspirador;
	}
	public void setPersonajeInspirador(PersonajeInspirador personajeInspirador) {
		this.personajeInspirador = personajeInspirador;
	}
	public long getCodigoGrupoTrabajo() {
		return codigoGrupoTrabajo;
	}
	public void setCodigoGrupoTrabajo(long codigoGrupoTrabajo) {
		this.codigoGrupoTrabajo = codigoGrupoTrabajo;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public List<SocioGrupoTrabajo> getSociosGrupoTrabajo() {
		return sociosGrupoTrabajo;
	}
	public void setSociosGrupoTrabajo(List<SocioGrupoTrabajo> sociosGrupoTrabajo) {
		this.sociosGrupoTrabajo = sociosGrupoTrabajo;
	}
	public Set<AreaCooperativa> getAreasCooperativa() {
		return areasCooperativa;
	}
	public void setAreasCooperativa(Set<AreaCooperativa> areasCooperativa) {
		this.areasCooperativa = areasCooperativa;
	}

}

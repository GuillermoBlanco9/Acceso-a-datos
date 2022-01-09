package es.iestetuan.dam2.vo;

import java.io.Serializable;
import java.util.Date;

public class SocioGrupoTrabajo implements Serializable{
	private Socio socio;
	private GrupoTrabajo grupoTrabajo;
	private Date fechaAlta;
	private Date fechaBaja;


	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public GrupoTrabajo getGrupoTrabajo() {
		return grupoTrabajo;
	}
	public void setGrupoTrabajo(GrupoTrabajo grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

}

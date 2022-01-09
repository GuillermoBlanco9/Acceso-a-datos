package es.iestetuan.dam2.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_SOCIO_GRUPOTRABAJO")
public class SocioGrupoTrabajo implements Serializable{
	@EmbeddedId
	private SocioGrupoTrabajoId id = new SocioGrupoTrabajoId();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod_socio", insertable = false, updatable = false, 
				foreignKey = @ForeignKey(name = "FK_SOCIO_GRUPOTRABAJO"))
	private Socio socio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod_grupotrabajo", insertable = false, updatable = false,
				foreignKey = @ForeignKey(name = "FK_GRUPOTRABAJO_SOCIO"))
	private GrupoTrabajo grupoTrabajo;
	
	@Column(name="fecha_alta")
	private Date fechaAlta;
	@Column(name="fecha_baja")
	private Date fechaBaja;

	/* getters y setters */
	public void setSocio(Socio socio) {
		this.socio = socio;
		this.id.codigoSocio=socio.getCodigoSocio();
	}
	public void setGrupoTrabajo(GrupoTrabajo grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
		this.id.codigoGrupoTrabajo=grupoTrabajo.getCodigoGrupoTrabajo();
	}
	public Socio getSocio() {
		return socio;
	}
	public GrupoTrabajo getGrupoTrabajo() {
		return grupoTrabajo;
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
	@Embeddable
	public static class SocioGrupoTrabajoId implements Serializable {		
		
		@Column(name = "cod_socio")
		private long codigoSocio;

		@Column(name = "cod_grupotrabajo")
		private long codigoGrupoTrabajo;
		
		public SocioGrupoTrabajoId() {
			
		}
		
		public SocioGrupoTrabajoId(long codigoSocio, long codigoGrupoTrabajo) {
			this.codigoSocio = codigoSocio;
			this.codigoGrupoTrabajo = codigoGrupoTrabajo;
			}
		
		public boolean equals(Object o) {
			boolean resultado=false;
			if (o != null && o instanceof SocioGrupoTrabajoId) {
				SocioGrupoTrabajoId that = (SocioGrupoTrabajoId) o;
				resultado= (this.codigoSocio==that.codigoSocio) && (this.codigoGrupoTrabajo == that.codigoGrupoTrabajo);
			}
			return resultado;
		}
		public int hashCode() {
			return Long.hashCode(codigoSocio)+ Long.hashCode(codigoGrupoTrabajo);
		}
		
	}


}

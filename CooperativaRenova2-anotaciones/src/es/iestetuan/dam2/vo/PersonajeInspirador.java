package es.iestetuan.dam2.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_PERSONAJEINSPIRADOR")
public class PersonajeInspirador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_personaje")
	private long codigoPersonaje;

	@OneToOne(mappedBy = "personajeInspirador")
	private GrupoTrabajo grupoTrabajo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="es_personajereal")
	private boolean indicadorPersonajeReal;

	/* getters y setters */
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

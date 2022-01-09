package es.iestetuan.dam2.vo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_GRUPOTRABAJO",
		indexes = @Index(name="INDICE_PERSONAJE", columnList="cod_personaje", unique = true)
		)
public class GrupoTrabajo {
	@Id
    @Column(name="cod_grupotrabajo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigoGrupoTrabajo;	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable( name = "T_GRUPOTRABAJO_AREACOOPERATIVA", 
		        joinColumns = { @JoinColumn(name = "cod_grupotrabajo") }, 
		        inverseJoinColumns = { @JoinColumn(name = "cod_areacooperativa") }
		    )
	private Set<AreaCooperativa> areasCooperativa;
	   
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_personaje", foreignKey = @ForeignKey(name = "FK_PERSONAJE"))
	private PersonajeInspirador personajeInspirador;
	
    @OneToMany(mappedBy="grupoTrabajo")
    private List<SocioGrupoTrabajo> sociosGrupoTrabajo;
	
	@Column(name="desc_corta")
	private String descripcionCorta;
	@Column(name="desc_larga")
	private String descripcionLarga;
	
	
	/* getters y setters */
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

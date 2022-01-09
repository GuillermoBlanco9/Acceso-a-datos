package es.iestetuan.dam2.vo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_AREACOOPERATIVA")
public class AreaCooperativa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_areacooperativa")
	private long codigoAreaCooperativa;
	
	@ManyToMany(mappedBy="areasCooperativa", fetch = FetchType.EAGER)
	private Set<GrupoTrabajo> gruposTrabajo;
    
	@Column(name="descripcion")
	private String descripcion;	
	
	
	/* getters y setters */
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

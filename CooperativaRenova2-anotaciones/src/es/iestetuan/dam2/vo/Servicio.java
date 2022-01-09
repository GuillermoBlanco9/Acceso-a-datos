package es.iestetuan.dam2.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_SERVICIO")
public class Servicio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="cod_servicio")
	private long codigoServicio;
	
	@OneToMany(mappedBy="servicio")
	private List<Contrato> contratos;
	
	@Column(name="descripcion")	
	private String descripcion;
	
	@Column(name="precio")
	private float precio;
	
	/* getters y setters */
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public long getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}

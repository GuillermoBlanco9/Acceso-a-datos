package es.iestetuan.dam2.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_SOCIO")
public class Socio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_socio")
	private long codigoSocio;
	
	@OneToMany(mappedBy="socio", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private List<Contrato> contratos;
	
    @OneToMany(mappedBy="socio")
	private List<SocioGrupoTrabajo> sociosGrupoTrabajo;
	
	@Column(name="nif")
	private String nif;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="telefono")
	private int telefono;
	
	@Column(name="num_cuenta")
	private String numeroCuenta;
	
	@Column(name="cantidad_inicial")
	private float cantidadInicialAportada;

	/* getters y setters */
	public List<SocioGrupoTrabajo> getSociosGrupoTrabajo() {
		return sociosGrupoTrabajo;
	}
	public void setSociosGrupoTrabajo(List<SocioGrupoTrabajo> sociosGrupoTrabajo) {
		this.sociosGrupoTrabajo =sociosGrupoTrabajo;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public long getCodigoSocio() {
		return codigoSocio;
	}
	public void setCodigoSocio(long codigoSocio) {
		this.codigoSocio = codigoSocio;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public float getCantidadInicialAportada() {
		return cantidadInicialAportada;
	}
	public void setCantidadInicialAportada(float cantidadInicialAportada) {
		this.cantidadInicialAportada = cantidadInicialAportada;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


}

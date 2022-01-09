package es.iestetuan.dam2.vo;

import java.util.List;

public class Socio {
	private long codigoSocio;
	private List<Contrato> contratos;
	private List<SocioGrupoTrabajo> sociosGrupoTrabajo;
	private String nif;
	private String nombre;
	private int telefono;
	private String numeroCuenta;
	private float cantidadInicialAportada;

	
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

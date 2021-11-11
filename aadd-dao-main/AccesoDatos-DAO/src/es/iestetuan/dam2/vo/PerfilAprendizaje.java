package es.iestetuan.dam2.vo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PerfilAprendizaje {
	private String id;
	private int orden;
	private String tipo;
	private String descripcion;
	private List<String> general;
	private List<String> sentimientos ;
	private List<String> comportamientos;
	private List<String> necesidades;
	private List<String> reconocimiento;
	private List<String> identificacion;
	@SerializedName("ayuda-interna") 
	private List<String> ayudaInterna;
	@SerializedName("ayuda-externa")	
	private List<String> ayudaExterna;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getGeneral() {
		return general;
	}
	public void setGeneral(List<String> general) {
		this.general = general;
	}
	public List<String> getSentimientos() {
		return sentimientos;
	}
	public void setSentimientos(List<String> sentimientos) {
		this.sentimientos = sentimientos;
	}
	public List<String> getComportamientos() {
		return comportamientos;
	}
	public void setComportamientos(List<String> comportamientos) {
		this.comportamientos = comportamientos;
	}
	public List<String> getNecesidades() {
		return necesidades;
	}
	public void setNecesidades(List<String> necesidades) {
		this.necesidades = necesidades;
	}
	public List<String> getReconocimiento() {
		return reconocimiento;
	}
	public void setReconocimiento(List<String> reconocimiento) {
		this.reconocimiento = reconocimiento;
	}
	public List<String> getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(List<String> identificacion) {
		this.identificacion = identificacion;
	}
	public List<String> getAyudaInterna() {
		return ayudaInterna;
	}
	public void setAyudaInterna(List<String> ayudaInterna) {
		this.ayudaInterna = ayudaInterna;
	}
	public List<String> getAyudaExterna() {
		return ayudaExterna;
	}
	public void addAyudaExterna(List<String> ayudaExterna) {
		this.ayudaExterna = ayudaExterna;
	}

}

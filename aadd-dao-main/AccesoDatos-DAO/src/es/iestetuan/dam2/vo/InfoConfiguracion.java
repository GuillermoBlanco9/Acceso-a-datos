package es.iestetuan.dam2.vo;

/**
 *  @descrition
 *	@author ineva
 *  @date 25 sept 2021
 *  @version 1.0
 *  @license GPLv3
 */

public class InfoConfiguracion {
	private String basedatos;
	private String usuario;
	private String clave;
	private String servidor;
	private String puerto;
	private String implementacion_usuario_dao;
	
	public String getBasedatos() {
		return basedatos;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getClave() {
		return clave;
	}
	public String getServidor() {
		return servidor;
	}
	public String getPuerto() {
		return puerto;
	}
	public String getImplementacion_usuario_dao() {
		return implementacion_usuario_dao;
	}
	public void setImplementacion_usuario_dao(String implementacion_usuario_dao) {
		this.implementacion_usuario_dao = implementacion_usuario_dao;
	}
}



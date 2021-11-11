package es.iestetuan.dam2.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarios")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Usuarios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuarios;

	@XmlElement(name = "usuario")
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> usuarios) {
		this.listaUsuarios = usuarios;
	}
}

package es.iestetuan.dam2.dao;

import java.util.List;

import es.iestetuan.dam2.vo.Usuario;

public interface IUsuarioDao {
	public Usuario getUsuario(int id);
	public void guardarUsuario(Usuario usuario);
	public List<Usuario> getListaUsuarios();
	public void guardarUsuarios(List<Usuario> listaUsuarios);
}

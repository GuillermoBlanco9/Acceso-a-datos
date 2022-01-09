package es.redmetro.dam2.dao;

import java.util.List;

public interface IBaseDeDatos <T> {
	public void crear(T entidad);
	public void modificar(T entidad);
	public void borrar(T entidad);
	public T consultarPorID(int codEntidad, Class<T> clase);
	public List<T> consultarLista(Class<T> clase);
}

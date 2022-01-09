package es.redmetro.dam2.dao;

import java.util.List;

public interface IoperacionesBBDD <bbdd> {
	public void crear(bbdd entidad);
	public void modificar(bbdd entidad);
	public void borrar(bbdd entidad);
	public bbdd consultarPorID(int codEntidad, Class<bbdd> clase);
	public List<bbdd> consultarLista(Class<bbdd> clase);
}

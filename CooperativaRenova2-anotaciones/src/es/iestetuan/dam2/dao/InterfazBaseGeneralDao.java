package es.iestetuan.dam2.dao;

import java.util.List;

import es.iestetuan.dam2.exception.Renova2CoopException;

public interface InterfazBaseGeneralDao <T> {
	public void crear(T entidad) throws Renova2CoopException;
	public  void borrar(T entidad) throws Renova2CoopException;
	public  void actualizar (T entidad) throws Renova2CoopException;
	public  T getEntidadPorID(long idEntidad) throws Renova2CoopException ;
	public  List<T> getListaEntidades() throws Renova2CoopException;
}

package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.GrupoTrabajo;

public class GrupoTrabajoHibernateDao implements InterfazBaseGeneralDao<GrupoTrabajo>{
	private Session sesion;

	@Override
	public void crear(GrupoTrabajo entidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		Transaction transaccion=null;
		try {
			sesion=UtilidadHibernate.getSession();
			transaccion=sesion.beginTransaction();
			
            sesion.save(entidad);
            
            transaccion.commit();
        } catch (HibernateException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CREAR, e);            
        } finally {
            sesion.close();
        }		
	}

	@Override
	public void borrar(GrupoTrabajo entidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		Transaction transaccion=null;
		try {
			sesion=UtilidadHibernate.getSession();
			transaccion=sesion.beginTransaction();
			
            sesion.delete(entidad);
            
            transaccion.commit();
        } catch (HibernateException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_BORRAR, e);            
        } finally {
            sesion.close();
        }		
		
	}

	@Override
	public void actualizar(GrupoTrabajo entidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		Transaction transaccion=null;
		try {
			sesion=UtilidadHibernate.getSession();
			transaccion=sesion.beginTransaction();
			
            sesion.update(entidad);
            
            transaccion.commit();
        } catch (HibernateException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_ACTUALIZAR, e);            
        } finally {
            sesion.close();
        }		
	
	}

	@Override
	public GrupoTrabajo getEntidadPorID(long idEntidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		GrupoTrabajo grupoTrabajo = null;
	    try {
			sesion=UtilidadHibernate.getSession();
	    	
			grupoTrabajo=sesion.get(GrupoTrabajo.class, idEntidad);            
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		
		return grupoTrabajo;
	}

	@Override
	public List<GrupoTrabajo> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<GrupoTrabajo> gruposTrabajo = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			gruposTrabajo = sesion.createNativeQuery("SELECT * FROM T_GRUPO_TRABAJO", GrupoTrabajo.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		

		return gruposTrabajo;
	}

}

package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.Servicio;

public class ServicioHibernateDao implements InterfazBaseGeneralDao<Servicio>{
	private Session sesion;

	@Override
	public void crear(Servicio entidad) throws Renova2CoopException{
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
	public void borrar(Servicio entidad) throws Renova2CoopException{
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
	public void actualizar(Servicio entidad) throws Renova2CoopException{
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
	public Servicio getEntidadPorID(long idEntidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		Servicio servicio = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			servicio=sesion.get(Servicio.class, idEntidad);
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		
		return servicio;
	}

	@Override
	public List<Servicio> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<Servicio> servicios = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			servicios = sesion.createNativeQuery("SELECT * FROM T_SERVICIO", Servicio.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	sesion.close();
        }		

		return servicios;
	}

}

package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.Socio;

public class SocioHibernateDao implements InterfazBaseGeneralDao<Socio>{
	private Session sesion;

	@Override
	public void crear(Socio entidad) throws Renova2CoopException{
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
	public void borrar(Socio entidad) throws Renova2CoopException{
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
	public void actualizar(Socio entidad) throws Renova2CoopException{
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
	public Socio getEntidadPorID(long idEntidad ) throws Renova2CoopException {
		// TODO Auto-generated method stub
		Socio socio= null;
	    try {
			sesion=UtilidadHibernate.getSession();

			socio=sesion.get(Socio.class, idEntidad);
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }	
	    return socio;
	}

	@Override
	public List<Socio> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<Socio> socios = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			socios = sesion.createNativeQuery("SELECT * FROM T_SOCIO", Socio.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	sesion.close();
        }		

		return socios;
	}

}

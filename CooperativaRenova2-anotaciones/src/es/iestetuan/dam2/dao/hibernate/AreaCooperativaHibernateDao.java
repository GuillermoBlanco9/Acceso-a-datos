package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.AreaCooperativa;


public class AreaCooperativaHibernateDao implements InterfazBaseGeneralDao<AreaCooperativa>{

	private Session sesion;

	@Override
	public void crear(AreaCooperativa entidad) throws Renova2CoopException{
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
	public void borrar(AreaCooperativa entidad) throws Renova2CoopException{
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
	public void actualizar(AreaCooperativa entidad) throws Renova2CoopException{
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
	public AreaCooperativa getEntidadPorID(long idEntidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		AreaCooperativa areaTrabajo = null;
	    try {
			sesion=UtilidadHibernate.getSession();
			
	    	areaTrabajo=sesion.get( AreaCooperativa.class, idEntidad);
	    	
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);
        } finally {
            sesion.close();
        }		

		return areaTrabajo;
	}

	@Override
	public List<AreaCooperativa> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<AreaCooperativa> areasTrabajo = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			areasTrabajo = sesion.createNativeQuery("SELECT * FROM T_AREACOOPERATIVA", AreaCooperativa.class).list();

	    } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);
        } finally {
            sesion.close();
        }		

		return areasTrabajo;
	}
}

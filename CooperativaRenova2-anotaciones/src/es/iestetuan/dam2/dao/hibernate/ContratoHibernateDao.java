package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.Contrato;

public class ContratoHibernateDao implements InterfazBaseGeneralDao<Contrato>{
	private Session sesion;

	@Override
	public void crear(Contrato entidad) throws Renova2CoopException{
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
	public void borrar(Contrato entidad) throws Renova2CoopException{
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
	public void actualizar(Contrato entidad) throws Renova2CoopException{
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
	public Contrato getEntidadPorID(long idEntidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		Contrato contrato = null;
	    try {
			sesion=UtilidadHibernate.getSession();
	    	contrato=sesion.get(Contrato.class, idEntidad);
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		
		return contrato;
	}

	@Override
	public List<Contrato> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<Contrato> contratos = null;
	    try {
			sesion=UtilidadHibernate.getSession();
	    	contratos = sesion.createNativeQuery("SELECT * FROM T_CONTRATO", Contrato.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		

		return contratos;
	}
	
}

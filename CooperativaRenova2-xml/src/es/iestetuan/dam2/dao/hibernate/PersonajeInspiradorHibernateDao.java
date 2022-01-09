package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.PersonajeInspirador;

public class PersonajeInspiradorHibernateDao implements InterfazBaseGeneralDao<PersonajeInspirador>{
	private Session sesion;

	@Override
	public void crear(PersonajeInspirador entidad) throws Renova2CoopException{
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
	public void borrar(PersonajeInspirador entidad) throws Renova2CoopException{
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
	public void actualizar(PersonajeInspirador entidad) throws Renova2CoopException{
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
	public PersonajeInspirador getEntidadPorID(long idEntidad) throws Renova2CoopException{
		// TODO Auto-generated method stub
		PersonajeInspirador personajeInspirador = null;
	    try {
			sesion=UtilidadHibernate.getSession();

	    	personajeInspirador=sesion.get(PersonajeInspirador.class, idEntidad);
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		
		return personajeInspirador;
	}

	@Override
	public List<PersonajeInspirador> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<PersonajeInspirador> personajesInspiradores = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			personajesInspiradores = sesion.createNativeQuery("SELECT * FROM T_PERSONAJE_INSPIRADOR", PersonajeInspirador.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }		

		return personajesInspiradores;
	}



}

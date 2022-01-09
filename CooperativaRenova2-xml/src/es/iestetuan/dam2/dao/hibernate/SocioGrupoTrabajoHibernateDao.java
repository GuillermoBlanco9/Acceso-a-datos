package es.iestetuan.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.UtilidadHibernate;
import es.iestetuan.dam2.vo.SocioGrupoTrabajo;

public class SocioGrupoTrabajoHibernateDao implements InterfazBaseGeneralDao<SocioGrupoTrabajo>{
	private Session sesion;

	@Override
	public void crear(SocioGrupoTrabajo entidad) throws Renova2CoopException{
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
	public void borrar(SocioGrupoTrabajo entidad) throws Renova2CoopException{
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
	public void actualizar(SocioGrupoTrabajo entidad) throws Renova2CoopException{
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
	public SocioGrupoTrabajo getEntidadPorID(long idEntidad ) throws Renova2CoopException {
		// TODO Auto-generated method stub
		SocioGrupoTrabajo socioGrupoTrabajo= null;
	    try {
			sesion=UtilidadHibernate.getSession();

			socioGrupoTrabajo=sesion.get(SocioGrupoTrabajo.class, idEntidad);
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
            sesion.close();
        }	
	    return socioGrupoTrabajo;
	}

	@Override
	public List<SocioGrupoTrabajo> getListaEntidades() throws Renova2CoopException{
		// TODO Auto-generated method stub
		List<SocioGrupoTrabajo> sociosGrupoTrabajo = null;
	    try {
			sesion=UtilidadHibernate.getSession();

			sociosGrupoTrabajo = sesion.createNativeQuery("SELECT * FROM T_SOCIO", SocioGrupoTrabajo.class).list();
        } catch (HibernateException e) {
            throw new  Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	sesion.close();
        }		

		return sociosGrupoTrabajo;
	}

}

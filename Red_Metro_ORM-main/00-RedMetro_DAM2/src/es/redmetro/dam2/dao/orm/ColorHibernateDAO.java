package es.redmetro.dam2.dao.orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseDeDatos;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Color;

public class ColorHibernateDAO implements IBaseDeDatos<Color> {
	
	public void crear(Color entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(entidad);
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public void modificar(Color entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(entidad);
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public void borrar(Color entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Color color = session.find(Color.class, entidad.getCodigoColor());
		
		if(color!=null) {
			session.delete(entidad);
			tx.commit();
		}
		else
			System.out.println("No existe el color a borrar");
		
		session.close();
	}

	public Color consultarPorID(int codEntidad, Class<Color> clase) {
		Session session = UtilidadHibernate.getSession();
		
		Color color = session.find(Color.class, codEntidad);
		
		if(color==null) 
			System.out.println("No existe el color");
		
		session.close();
		return color;
	}

	public List<Color> consultarLista(Class<Color> clase) {
		List<Color> listaColores = null;
		Session session = UtilidadHibernate.getSession();
		
		listaColores = session.createNativeQuery("SELECT * FROM T_COLOR", Color.class).list();
		return listaColores;
	}
	
}

package es.iestetuan.dam2;

import java.util.List;

import es.iestetuan.dam2.dao.IDepartamento;
import es.iestetuan.dam2.dao.jdbc.DepartamentoJdbcDao;
import es.iestetuan.dam2.vo.Departamento;

public class AplicacionDaoJdbc {

	public static void main(String[] args) {
		// Implementación JDBC
    	IDepartamento operacionesDepartamento = new DepartamentoJdbcDao();
    	
    	// Consulta 
    	Departamento departamento = operacionesDepartamento.consultarDepartamento(50);
    	if(departamento!=null)
    		System.out.println(departamento);
    	
    	// Creación de un departamento
    	Departamento dept = new Departamento();
    	dept.setNumeroDepartamento(60);
    	dept.setNombreDepartamento("TIC y TACA");
    	dept.setLocalidad("Santander");
    	operacionesDepartamento.crearDepartamento(dept);
    	
    	// Borrado de un departamento
    	operacionesDepartamento.borrarDepartamento(60);
    	
    	// Modificación de datos de Departamento
    	// Para este ejemplo se cambia la información del departamento dado de alta
    	Departamento dept1 = new Departamento();
    	dept1.setNumeroDepartamento(20);
    	dept1.setNombreDepartamento("TRAZBILIDAD");
    	dept1.setLocalidad("Madrid");
    	operacionesDepartamento.modificarDepartamento(dept1);

    	// Consulta de datos con llamada a procedimienot almacenado
    	List<Departamento> listaDptos= operacionesDepartamento.consultarDepartamentosNombre("V");
    	for (Departamento depart : listaDptos) {
        	System.out.println(depart);
		}

	}
}

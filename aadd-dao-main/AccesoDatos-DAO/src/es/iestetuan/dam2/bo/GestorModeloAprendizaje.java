package es.iestetuan.dam2.bo;

import java.util.Scanner;

import es.iestetuan.dam2.dao.IModeloAprendizajeDao;
import es.iestetuan.dam2.dao.json.ModeloAprendizajeJSONDAO;
import es.iestetuan.dam2.dao.xml.ModeloAprendizajeXMLDAO;
import es.iestetuan.dam2.vo.ModeloAprendizaje;

public class GestorModeloAprendizaje {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorModeloAprendizaje gma = new GestorModeloAprendizaje();
		/*
		Scanner nombreObjeto = new Scanner(System.in);
		int id = nombreObjeto.nextInt();
		nombreObjeto.close();
		*/
		int id = 1;
		gma.imprimirModeloAprendizaje(id);
	}
	public void imprimirModeloAprendizaje(int id) {
/*
		IModeloAprendizajeDao modeloApreDAO = new ModeloAprendizajeXMLDAO();
		ModeloAprendizaje modeloAprendizaje =modeloApreDAO.getModeloAprendizaje(id);
		
		System.out.println(modeloAprendizaje);
	
		IModeloAprendizajeDao modeloApreDAO = new ModeloAprendizajeXMLDAO();
		modeloApreDAO.crearXML();
*/		
		IModeloAprendizajeDao modeloApreDAO = new ModeloAprendizajeJSONDAO();
		modeloApreDAO.getModeloAprendizaje(1);
	
		
	}
}
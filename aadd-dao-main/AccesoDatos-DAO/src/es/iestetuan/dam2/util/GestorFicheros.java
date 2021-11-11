package es.iestetuan.dam2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class GestorFicheros {
	private final static String SEPARADOR=System.getProperty("file.separator");
	
	public static void main(String[] args) {
		System.out.println(System.getenv());
		System.out.println(SEPARADOR);
		String rutaGeneral= "C:\\tmp\\proceso";
		String rutaProcesados= rutaGeneral+ SEPARADOR + "procesados";
		String rutaBackup= rutaGeneral+ SEPARADOR + "backup";
		String rutaTmp= rutaGeneral+ SEPARADOR +"tmp";
		String rutaTmp2= rutaTmp+ SEPARADOR+"tmp";
		String ficheroR= rutaTmp2+ SEPARADOR + "info.txt";
		
		crearDirectorio(rutaGeneral);
		crearDirectorio(rutaProcesados);
		crearDirectorio(rutaBackup);
		crearDirectorio(rutaTmp);
		crearDirectorio(rutaTmp2);
		try {
			crearFichero(ficheroR);
			// File archivo = new File(rutaTmp);
			//borrarArchivo(archivo);
			borrarArchivoNIO(rutaTmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void crearDirectorio(String rutaDirectorio) {
		File directorio = new File(rutaDirectorio);
		System.out.println("¿Existe el director " + directorio.getName() + " ? " + directorio.exists());
		if (!directorio.exists()) {
			directorio.mkdir();		
		}
	}
	public static void crearFichero(String rutaFichero) throws IOException {
		File fichero = new File(rutaFichero);
		System.out.println("¿Existe el fichero" + fichero.getName() + " ? " + fichero.exists());
		if (!fichero.exists()) {
			fichero.createNewFile();		
		}
	}
	
	public static void borrarArchivo(File archivo) throws FileNotFoundException {
		//System.out.println("Previo borrado ARCHIVO: ¿Existe el archivo " + archivo.getName() + " ? " + archivo.exists());

		if (archivo.isDirectory()) {
			for (File c : archivo.listFiles()) {
				//Borrado recursivo
				borrarArchivo(c);
			}
		}
		if (!archivo.delete()) {
			throw new FileNotFoundException("Error al borrar fichero: " + archivo);
		}
	}

	public static void borrarArchivoNIO(String rutaArhivo) throws IOException {
		Path directory = Paths.get(rutaArhivo);
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
		   @Override
		   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		       Files.delete(file);
		       return FileVisitResult.CONTINUE;
		   }
	
		   @Override
		   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		       Files.delete(dir);
		       return FileVisitResult.CONTINUE;
		   }
		});
	}
}

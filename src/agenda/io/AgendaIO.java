package agenda.io;

//@author Eneko Seminario y Nikolay Petrov
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 */
public class AgendaIO {

	public static int importar(AgendaContactos agenda, File fichero) throws FileNotFoundException {
		int errores = 0;
		//Scanner input = new Scanner(agenda.getClass().getResourceAsStream(fichero));
		Scanner input = new Scanner(fichero);
		while (input.hasNextLine()) {
			String linea = input.nextLine();
			try {
				Contacto contacto = parsearLinea(linea);
				agenda.anadirContacto(contacto);
			} catch (Exception e) {
				errores++;
				e.printStackTrace();
			}

		}
		input.close();
		return errores;
	}

	private static Contacto parsearLinea(String linea) {

		String[] partes = linea.split(",");

		int tipoContacto = Integer.parseInt(partes[0].trim());

		String nombre = partes[1].trim();
		String apellidos = partes[2].trim();
		String telefono = partes[3].trim();
		String email = partes[4].trim();
		String empresa;
		String fechaNacimiento;
		Relacion relacion;

		switch (tipoContacto) {
		case 1:
			empresa = partes[5].trim();
			return new Profesional(nombre, apellidos, telefono, email, empresa);

		case 2:
			fechaNacimiento =partes[5].trim();
			relacion = Relacion.valueOf(partes[6].trim().toUpperCase());
			return new Personal(nombre, apellidos, telefono, email, fechaNacimiento, relacion);
		}
		return null;
	}

	public static void exportarPersonales(AgendaContactos agenda, String fichero) throws IOException 
	{
		File f = new File(fichero);
			PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(new File(fichero))));
				Map<Relacion, Set<String>> map = agenda.personalesPorRelacion(); 	
		salida.close();
	}

}
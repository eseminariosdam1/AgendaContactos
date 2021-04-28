package ut7.agenda.io;

//@author Eneko Seminario y Nikolay Petrov
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ut7.agenda.modelo.AgendaContactos;
import ut7.agenda.modelo.Contacto;
import ut7.agenda.modelo.Personal;
import ut7.agenda.modelo.Profesional;
import ut7.agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 */
public class AgendaIO {

	public static int importar(AgendaContactos agenda, String fichero) {
		int errores = 0;
		Scanner input = new Scanner(agenda.getClass().getResourceAsStream(fichero));
		while (input.hasNextLine()) {
			String linea = input.nextLine();
			try {
				Contacto contacto = parsearLinea(linea);
				agenda.anadirContacto(contacto);
			} catch (Exception e) {
				errores++;
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
			relacion = Relacion.valueOf(partes[6].trim());
			return new Personal(nombre, apellidos, telefono, email, fechaNacimiento, relacion);
		}
		return null;
	}

}
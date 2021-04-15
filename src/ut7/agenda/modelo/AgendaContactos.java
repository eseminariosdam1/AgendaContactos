package ut7.agenda.modelo;
//@author Eneko Seminario y Nikolay Petrov
//@author Eneko Seminario y Nikolay Petrov
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {

	}

	public void anadirContacto(Contacto contacto) {

	}

	public void contactosEnLetra() {

	}

	public void totalContactos() {

	}

	@Override
	public String toString() {

		return null;
	}
	/*
	 * Devuelve una colección ArrayList con todos los contactos (personales o
	 * profesionales) que incluyen el texto indicado en su nombre o apellidos.
	 */
	public List<Contacto> buscarContactos(String texto) {
		List<Contacto> contactosEncontrados = new ArrayList<Contacto>();
		for (Map.Entry<Character, Set<Contacto>> entradaAgenda : agenda.entrySet()) {
			for (Contacto contacto : entradaAgenda.getValue()) {
				if (contacto.getNombre().contains(texto) || contacto.getApellidos().contains(texto)) {
					contactosEncontrados.add(contacto);
				}
			}
		}

		return contactosEncontrados;
	}

	/*
	 * Dada una letra devuelve una colección ArrayList con los contactos personales
	 * en esa letra. Si la letra no está en la agenda se devuelve null.
	 */
	public List<Personal> personalesEnLetra(char letra) {
		List<Personal> personalesEncontrados = new ArrayList<Personal>();
		for (Map.Entry<Character, Set<Contacto>> entradaAgenda : agenda.entrySet()) {
			if (entradaAgenda.getKey() == letra) {
				for (Contacto contacto : entradaAgenda.getValue()) {
					if (contacto.getClass() == Personal.class) {
						if (contacto.getPrimeraLetra() == letra) {
							personalesEncontrados.add((Personal) contacto);
						}
					}
				}
			}

		}

		return personalesEncontrados;
	}
	/*
	 * Devuelve una colección ArrayList con todos los contactos personales a los que
	 * hay que felicitar.
	 */
	public List<Personal> felicitar() {
		List<Personal> personalesAFelicitar = new ArrayList<Personal>();
		for (Map.Entry<Character, Set<Contacto>> entradaAgenda : agenda.entrySet()) {
			for (Contacto contacto : entradaAgenda.getValue()) {
				if (contacto.getClass() == Personal.class) {
					if (((Personal) contacto).esCumpleaños()) {
						personalesAFelicitar.add((Personal) contacto);
					}
				}
			}

		}
		return personalesAFelicitar;
	}
	/*
	 * Devuelve un nuevo map en el que aparecen solo contactos personales pero
	 * organizados de forma que la clave en el nuevo map es la relación (un
	 * enumerado) y el valor asociado una colección List de cadenas con los
	 * apellidos y nombre de todos los contactos personales que hay en la agenda.
	 * Las claves se recuperan en el orden natural del enumerado.
	 */
	public Map<Relacion, Set<String>> personalesPorRelacion() {
		// Creamos nuevo mapa para albergar resultados
		Map<Relacion, Set<String>> personalesRelacionados = new HashMap<Relacion, Set<String>>();
		// recorremos los valores del enumerado relacion
		for (Relacion relacion : Relacion.values()) {
			// creamos nueva entrada para el mapa de resultados
			Entry<Relacion, Set<String>> entrada = Map.entry(relacion, new HashSet<String>());
			// recorremos las entradas de la agenda
			for (Map.Entry<Character, Set<Contacto>> entradaAgenda : agenda.entrySet()) {
				// recorremos los contactos de la entrada
				for (Contacto contacto : entradaAgenda.getValue()) {
					// preguntamos si el contacto es de tipo personal
					if (contacto.getClass() == Personal.class) {
						// convertimos el contact en contacot personal
						Personal contactoPersonal = (Personal) contacto;
						// preguntamos si la relacion del contacto personal es la relacion actual
						if (contactoPersonal.getRelacion() == relacion) {
							// obtenemos nombre y apellidos el contacto
							String nombreApellidos = contactoPersonal.getNombre() + " , "
									+ contactoPersonal.getApellidos();
							// añadimos los nombre y apellidos a la coleccion de la entrada nueva
							entrada.getValue().add(nombreApellidos);
						}
					}
				}

			}
		}

		return personalesRelacionados;
	}

	/*
	 * Dada una letra devuelve los contactos personales ordenados por fecha de
	 * nacimiento (ascendente)
	 */
	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {
		List<Personal> personalesEncontrados = personalesEnLetra(letra);
		 Collections.sort(personalesEncontrados, (Personal p1, Personal p2)-> p1.getFechaNacimiento().compareTo(p2.getFechaNacimiento()));
		 return personalesEncontrados;

	}


}

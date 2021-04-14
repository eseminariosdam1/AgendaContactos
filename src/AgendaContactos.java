
//@author Eneko Seminario y Nikolay Petrov
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {

	}

	public void añadirContacto() {

	}

	public void contactosEnLetra() {

	}

	public void totalContactos() {

	}

	@Override
	public String toString() {

		return null;
	}

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

	public List<Personal> personalesEnLetra(char letra) {

		return null;
	}

	public List<Personal> felicitar() {

		return null;
	}

	public void personalesPorRelacion() {

	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {

		return null;

	}

}

//@author Eneko Seminario y Nikolay Petrov
import java.util.Random;

public class Profesional extends Contacto {
	private String empresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String empresa) {
		super(nombre, apellidos, telefono, email);
		this.empresa = capitalizeString(empresa);
	}

	// https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
	//He mirado en esta página y creo que se puede hacer asi(no se hacerlo de otra manera)
	public static String capitalizeString(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i])) {
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	public String getEmpresa() {
		return empresa;
	}

	public String getFirmaEmail() {
		String[] firmas = new String[] { "Atentamente", "Saludos", "Saludos cordiales", "Mis mejores deseos" };
		int indiceAleatorio = new Random().nextInt(firmas.length - 1);
		return firmas[indiceAleatorio];
	}

	@Override
	public String toString() {
		String texto = getApellidos().toUpperCase() + ", " + getNombre().toUpperCase() + " (PROFESIONAL) \n";
		texto += "Tfno : " + getTelefono() + " | email: " + getEmail() + "\n ";
		texto += "Empresa: " + empresa;
		return texto;
	}
}

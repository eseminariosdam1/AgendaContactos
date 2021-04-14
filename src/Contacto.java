//@Autor: Eenko Seminario  y  Nikolay Petrov

public abstract class Contacto implements Comparable<Contacto>
{
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;

	public Contacto(String nombre, String apellidos, String telefono,
			String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return email.hashCode();

	}
	
	
	public char getPrimeraLetra() 
	{
		return apellidos.charAt (0);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	 
	public int compareTo(Contacto contactoA) 
	{
		int Comparador = apellidos.compareTo(contactoA.getApellidos() );
	
		if (Comparador == 0 ) 
		{
			Comparador = nombre.compareTo(contactoA.getNombre());
		}
		return Comparador;	
	}
		
	
	public abstract String getFirmaEmail();
	
	
	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email
				+ "]";
	}
	
}

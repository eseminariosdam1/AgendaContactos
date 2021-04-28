package ut7.agenda.modelo;
//@Autor: Eenko Seminario y Nikolay Petrov 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personal extends Contacto {
	private Relacion relacion;
	private LocalDate fechaNacimiento;

	
	public Personal(String nombre, String apellidos, String telefono, String email, String fechaNacimiento, Relacion relacion) {
		super(nombre, apellidos, telefono, email);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.fechaNacimiento = LocalDate.parse(fechaNacimiento, formatter);;
		
		this.relacion = relacion;
	}


	public Relacion getRelacion() {
		return relacion;
	}

	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	
	public String getFirmaEmail() 
	{
		return "Un abrazo!";
	}
	
	
	public boolean esCumpleaños() {
		LocalDate fechaActual = LocalDate.now();
		return (fechaNacimiento.getMonthValue() == fechaActual.getMonthValue()) && (fechaNacimiento.getDayOfMonth() == fechaActual.getDayOfMonth()) ;
	}

	
	@Override
	public String toString() {
		String str = super.toString();
		int mes = fechaNacimiento.getMonthValue();
		str += "Fecha nacimiento: " + fechaNacimiento.getDayOfMonth() + " ";
		
		switch(mes) 
		{
		case 1: 
			str += "ene.";
		case 2: 
			str += "feb.";
		case 3: 
			str += "mar.";
		case 4: 
			str += "abr.";
		case 5: 
			str += "may.";
		case 6: 
			str += "jun.";
		case 7: 
			str += "jul.";
		case 8: 
			str += "ago.";
		case 9: 
			str += "sep.";
		case 10: 
			str += "oct.";
		case 11: 
			str += "nov.";
		case 12: 
			str += "dec.";
		}
		
		str += " " + fechaNacimiento.getYear() + "\nRelacion: " + relacion.toString();
	return str;
	}


}

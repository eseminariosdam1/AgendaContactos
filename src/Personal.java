//@author Eneko Seminario y Nikolay Petrov
		import java.time.LocalDate;

		public class Personal extends Contacto {
			private Relacion relacion;
			private LocalDate fechaNacimiento;

			public Personal(String nombre, String apellidos, String telefono, String email, LocalDate fechaNacimiento, Relacion relacion) {
				super(nombre, apellidos, telefono, email);
				this.fechaNacimiento = fechaNacimiento;
				this.relacion = relacion;
			}

			public boolean esCumpleaños() {
				return true;

			}

			public Relacion getRelacion() {
				return relacion;
			}

			public LocalDate getFechaNacimiento() {
				return fechaNacimiento;
			}
		}
	}

}

module AgendaContactos {
	requires javafx.controls;
	
	opens agenda.interfaz to javafx.graphics, javafx.fxml;
}

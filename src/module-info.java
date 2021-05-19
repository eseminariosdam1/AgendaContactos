module AgendaContactos {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens agenda.interfaz to javafx.graphics, javafx.fxml;
}

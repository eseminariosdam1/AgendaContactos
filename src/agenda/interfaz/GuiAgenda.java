package agenda.interfaz;

import agenda.modelo.AgendaContactos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GuiAgenda extends Application {
	private AgendaContactos agenda;
	private MenuItem itemImportar;
	private MenuItem itemExportarPersonales;
	private MenuItem itemSalir;

	private MenuItem itemBuscar;
	private MenuItem itemFelicitar;

	private MenuItem itemAbout;

	private TextArea areaTexto;

	private RadioButton rbtListarTodo;
	private RadioButton rbtListarSoloNumero;
	private Button btnListar;

	private Button btnPersonalesEnLetra;
	private Button btnPersonalesOrdenadosPorFecha;

	private TextField txtBuscar;

	private Button btnClear;
	private Button btnSalir;

	@Override
	public void start(Stage stage) {
		agenda = new AgendaContactos(); // el modelo

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 1100, 700);
		stage.setScene(scene);
		stage.setTitle("Agenda de contactos");
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		stage.show();

	}

	private BorderPane crearGui() {
		BorderPane panel = new BorderPane();
		panel.setTop(crearBarraMenu());
		panel.setCenter(crearPanelPrincipal());
		return panel;
	}

	private BorderPane crearPanelPrincipal() {
		BorderPane panel = new BorderPane();
		panel.setPadding(new Insets(10));
		panel.setTop(crearPanelLetras());

		areaTexto = new TextArea();
		areaTexto.getStyleClass().add("textarea");
		panel.setCenter(areaTexto);

		panel.setLeft(crearPanelBotones());
		return panel;
	}

	private VBox crearPanelBotones() {
		// a completar
		VBox panel = new VBox(10);
		panel.setPadding(new Insets(10));
		panel.setSpacing(10);
		BorderPane bordeBuscar = new BorderPane();
		TextField txtBuscar = new TextField();
		BorderPane.setMargin(txtBuscar, new Insets(0, 0, 40, 0));

		txtBuscar.setPromptText("Buscar");
		txtBuscar.setMinHeight(40);
		txtBuscar.getStyleClass().add("text-field");
		txtBuscar.setOnAction(event -> {
			System.out.println();
		});
		bordeBuscar.setTop(txtBuscar);
		ToggleGroup grupo = new ToggleGroup();
		RadioButton radioListarAgenda = new RadioButton("Listar toda la agenda");
		radioListarAgenda.setSelected(true);
		radioListarAgenda.getStyleClass().add("radio-button");
		radioListarAgenda.setOnAction(event -> {
		
		});
		radioListarAgenda.setToggleGroup(grupo);
		RadioButton radioListarContactos = new RadioButton("Listar nº contactos");
		radioListarContactos.getStyleClass().add("radio-button");
		radioListarContactos.setOnAction(event -> {
			System.out.println();
		});
		radioListarContactos.setToggleGroup(grupo);
		Button botonListar = new Button("Listar");
		botonListar.setPrefWidth(250);
		botonListar.getStyleClass().add("botones");
		botonListar.setOnAction(event -> {
			System.out.println();
		});

		BorderPane bordeListar = new BorderPane();
		BorderPane.setMargin(botonListar, new Insets(0,0,40,0));
		bordeListar.setTop(botonListar);
		Button botonEnLetra = new Button("Contactos Personales en letra");
		botonEnLetra.setPrefWidth(250);
		botonEnLetra.getStyleClass().add("botones");
		botonEnLetra.setOnAction(event -> {
			System.out.println();
		});

		
		Button botonOrdenados = new Button("Contactos personales ordenados por fecha");
		botonOrdenados.setPrefWidth(250);
		botonOrdenados.setWrapText(true);;
		botonOrdenados.getStyleClass().add("botones");
		botonOrdenados.setTextAlignment(TextAlignment.CENTER);
		botonOrdenados.setOnAction(event -> {
			System.out.println();
		});

		Button botonclear = new Button("Clear");
		botonclear.setPrefWidth(250);
		botonclear.getStyleClass().add("botones");
		botonclear.setOnAction(event -> {
			clear();
		});
		
		BorderPane bordeClear = new BorderPane();
		BorderPane.setMargin(botonclear, new Insets(40,0,0,0));
		bordeClear.setTop(botonclear);
		Button botonSalir = new Button("Salir");
		botonSalir.setPrefWidth(250);
		botonSalir.getStyleClass().add("botones");
		botonSalir.setOnAction(event -> {
			salir();
		});

		
		panel.getChildren().add(bordeBuscar);
		panel.getChildren().add(radioListarAgenda);
		panel.getChildren().add(radioListarContactos);
		panel.getChildren().add(bordeListar);
		panel.getChildren().add(botonEnLetra);
		panel.getChildren().add(botonOrdenados);
		panel.getChildren().add(bordeClear);
		panel.getChildren().add(botonSalir);
		return panel;
	}

	private GridPane crearPanelLetras() {
		// a completar
		GridPane panel = new GridPane();
		panel.setHgap(5);
		panel.setVgap(5);
		panel.setPadding(new Insets(10));
		String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		int columna = 0;
		int fila = 0;
		for (char letra : abecedario.toCharArray()) {
			Button boton = new Button(Character.toString(letra));
			boton.setPadding(new Insets(10, 20, 10, 20));
			boton.setMaxWidth(Double.MAX_VALUE);
			boton.getStyleClass().add("botonletra");
			boton.setOnAction(event -> {
				System.out.println(letra);
			});
			if (columna > abecedario.length() / 2) {
				columna = 0;
				fila++;
			}
			panel.add(boton, columna, fila);
			columna++;
		}

		return panel;
	}

	private void crearMenuArchivo(MenuBar barra) {
		Menu menuArchivo = new Menu("Archivo");
		MenuItem item1 = new MenuItem("Importar Agenda");
		KeyCombination kc = new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN);
		item1.setAccelerator(kc);
		item1.setOnAction(event -> {

		});

		MenuItem item2 = new MenuItem("Exportar Personales");
		item2.setDisable(true);
		kc = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
		item2.setAccelerator(kc);
		item2.setOnAction(event -> {

		});

		SeparatorMenuItem separador = new SeparatorMenuItem();

		MenuItem item3 = new MenuItem("Salir");
		kc = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
		item3.setAccelerator(kc);
		item3.setOnAction(event -> {
			salir();
		});

		menuArchivo.getItems().add(item1);
		menuArchivo.getItems().add(item2);
		menuArchivo.getItems().add(separador);
		menuArchivo.getItems().add(item3);

		barra.getMenus().add(menuArchivo);
	}

	private void crearMenuOperaciones(MenuBar barra) {

		Menu menuOperaciones = new Menu("Operaciones");
		MenuItem item1 = new MenuItem("Buscar");
		KeyCombination kc = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
		item1.setAccelerator(kc);
		item1.setOnAction(event -> {

		});
		MenuItem item2 = new MenuItem("Felicitar");
		kc = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
		item2.setAccelerator(kc);
		item2.setOnAction(event -> {

		});
		menuOperaciones.getItems().add(item1);
		menuOperaciones.getItems().add(item2);
		barra.getMenus().add(menuOperaciones);

	}

	private void crearMenuHelp(MenuBar barra) {
		Menu menuHelp = new Menu("Help");
		MenuItem item1 = new MenuItem("About");
		KeyCombination kc = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
		item1.setAccelerator(kc);
		item1.setOnAction(event -> {

		});
		menuHelp.getItems().add(item1);

		barra.getMenus().add(menuHelp);
	}

	private MenuBar crearBarraMenu() {
		// a completar
		MenuBar barra = new MenuBar();

		crearMenuArchivo(barra);
		crearMenuOperaciones(barra);
		crearMenuHelp(barra);
		return barra;
	}

	private void importarAgenda() {
		// a completar

	}

	private void exportarPersonales() {
		// a completar

	}

	/**
	 *  
	 */
	private void listar() {
		clear();
		// a completar

	}

	private void personalesOrdenadosPorFecha() {
		clear();
		// a completar

	}

	private void contactosPersonalesEnLetra() {
		clear();
		// a completar

	}

	private void contactosEnLetra(char letra) {
		clear();
		// a completar
	}

	private void felicitar() {
		clear();
		// a completar

	}

	private void buscar() {
		clear();
		// a completar

		cogerFoco();

	}

	private void about() {
		// a completar

	}

	private void clear() {
		areaTexto.setText("");
	}

	private void salir() {
		Platform.exit();
	}

	private void cogerFoco() {
		txtBuscar.requestFocus();
		txtBuscar.selectAll();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

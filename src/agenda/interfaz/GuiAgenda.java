//@author: Eneko Seminario y Nikolay Petrov
package agenda.interfaz;

import java.io.File;
import java.io.FileNotFoundException;

import agenda.io.AgendaIO;
import agenda.modelo.AgendaContactos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
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
import javafx.stage.FileChooser;
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
	private Stage _stage;

	@Override
	public void start(Stage stage) {
		_stage = stage;
		agenda = new AgendaContactos(); // el modelo

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 1100, 700);
		stage.setScene(scene);
		stage.setTitle("Agenda de contactos");
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		stage.show();

	}

	private BorderPane crearGui() {
		//creamos la gui
		BorderPane panel = new BorderPane();
		panel.setTop(crearBarraMenu());
		panel.setCenter(crearPanelPrincipal());
		return panel;
	}

	private BorderPane crearPanelPrincipal() {
		//creamos el panel principal con sus respectivas caracteristicas
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
		// a completar creamos el panel y le damos padding y spacing
		VBox panel = new VBox(10);
		panel.setPadding(new Insets(10));
		panel.setSpacing(10);
		
		//configuramos borderPane para dar margen al campo de texto Buscar configuramos el texto buscar dando la altura y la clase necesaria
		BorderPane bordeBuscar = new BorderPane();
		txtBuscar = new TextField();
		BorderPane.setMargin(txtBuscar, new Insets(0, 0, 40, 0));

		txtBuscar.setPromptText("Buscar");
		txtBuscar.setMinHeight(40);
		txtBuscar.getStyleClass().add("text-field");
		txtBuscar.setOnAction(event -> {
			System.out.println();
		});
		bordeBuscar.setTop(txtBuscar);
		ToggleGroup grupo = new ToggleGroup();
		rbtListarTodo = new RadioButton("Listar toda la agenda");
		rbtListarTodo.setSelected(true);
		rbtListarTodo.getStyleClass().add("radio-button");
		rbtListarTodo.setOnAction(event -> {

		});
		rbtListarTodo.setToggleGroup(grupo);
		rbtListarSoloNumero = new RadioButton("Listar nº contactos");
		rbtListarSoloNumero.getStyleClass().add("radio-button");
		rbtListarSoloNumero.setOnAction(event -> {
			System.out.println();
		});
		rbtListarSoloNumero.setToggleGroup(grupo);
		btnListar = new Button("Listar");
		btnListar.setPrefWidth(250);
		btnListar.getStyleClass().add("botones");
		btnListar.setOnAction(event -> {
			System.out.println();
		});

		BorderPane bordeListar = new BorderPane();
		BorderPane.setMargin(btnListar, new Insets(0, 0, 40, 0));
		bordeListar.setTop(btnListar);
		btnPersonalesEnLetra = new Button("Contactos Personales en letra");
		btnPersonalesEnLetra.setPrefWidth(250);
		btnPersonalesEnLetra.getStyleClass().add("botones");
		btnPersonalesEnLetra.setOnAction(event -> {
			System.out.println();
		});

		btnPersonalesOrdenadosPorFecha = new Button("Contactos personales ordenados por fecha");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);
		btnPersonalesOrdenadosPorFecha.setWrapText(true);
		;
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");
		btnPersonalesOrdenadosPorFecha.setTextAlignment(TextAlignment.CENTER);
		btnPersonalesOrdenadosPorFecha.setOnAction(event -> {
			System.out.println();
		});

		btnClear = new Button("Clear");
		btnClear.setPrefWidth(250);
		btnClear.getStyleClass().add("botones");
		btnClear.setOnAction(event -> {
			clear();
		});
		//damos amrgen y altura a los botones
		BorderPane bordeClear = new BorderPane();
		BorderPane.setMargin(btnClear, new Insets(40, 0, 0, 0));
		bordeClear.setTop(btnClear);
		btnSalir = new Button("Salir");
		btnSalir.setPrefWidth(250);
		btnSalir.getStyleClass().add("botones");
		btnSalir.setOnAction(event -> {
			salir();
		});

		//añado los controls al panel
		panel.getChildren().add(bordeBuscar);
		panel.getChildren().add(rbtListarTodo);
		panel.getChildren().add(rbtListarSoloNumero);
		panel.getChildren().add(bordeListar);
		panel.getChildren().add(btnPersonalesEnLetra);
		panel.getChildren().add(btnPersonalesOrdenadosPorFecha);
		panel.getChildren().add(bordeClear);
		panel.getChildren().add(btnSalir);
		return panel;
	}

	private GridPane crearPanelLetras() {
		// a completar configuro el panel
		GridPane panel = new GridPane();
		panel.setHgap(5);
		panel.setVgap(5);
		panel.setPadding(new Insets(10));
		String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		int columna = 0;
		int fila = 0;
		
		//recorro el abecedario y genero un boton por cada letra
		for (char letra : abecedario.toCharArray()) {
			Button boton = new Button(Character.toString(letra));
			boton.setPadding(new Insets(10, 20, 10, 20));
			boton.setMaxWidth(Double.MAX_VALUE);
			boton.getStyleClass().add("botonletra");
			boton.setOnAction(event -> {
				System.out.println(letra);
			});
			
			//Hago un if para que los botnes salgan en dos filas
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
		//creamos el menu para importar la agenda
		Menu menuArchivo = new Menu("Archivo");
		itemImportar = new MenuItem("Importar Agenda");
		KeyCombination kc = new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN);
		itemImportar.setAccelerator(kc);
		itemImportar.setOnAction(event -> {
			try {
				importarAgenda();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		itemExportarPersonales = new MenuItem("Exportar Personales");
		itemExportarPersonales.setDisable(true);
		kc = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
		itemExportarPersonales.setAccelerator(kc);
		itemExportarPersonales.setOnAction(event -> {

		});

		SeparatorMenuItem separador = new SeparatorMenuItem();

		itemSalir = new MenuItem("Salir");
		kc = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
		itemSalir.setAccelerator(kc);
		itemSalir.setOnAction(event -> {
			salir();
		});

		menuArchivo.getItems().add(itemImportar);
		menuArchivo.getItems().add(itemExportarPersonales);
		menuArchivo.getItems().add(separador);
		menuArchivo.getItems().add(itemSalir);

		barra.getMenus().add(menuArchivo);
	}

	private void crearMenuOperaciones(MenuBar barra) {
		//creamos el menu operaciones con sus respectivas opciones
		Menu menuOperaciones = new Menu("Operaciones");
		itemBuscar = new MenuItem("Buscar");
		KeyCombination kc = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
		itemBuscar.setAccelerator(kc);
		itemBuscar.setOnAction(event -> {

		});
		itemFelicitar = new MenuItem("Felicitar");
		kc = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
		itemFelicitar.setAccelerator(kc);
		itemFelicitar.setOnAction(event -> {

		});
		menuOperaciones.getItems().add(itemBuscar);
		menuOperaciones.getItems().add(itemFelicitar);
		barra.getMenus().add(menuOperaciones);

	}

	private void crearMenuHelp(MenuBar barra) {
		//creamos el menu help con las distintas opciones con sus respectivas combinacions de teclas
		Menu menuHelp = new Menu("Help");
		itemAbout = new MenuItem("About");
		KeyCombination kc = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
		itemAbout.setAccelerator(kc);
		itemAbout.setOnAction(event -> {
			about();
		});
		menuHelp.getItems().add(itemAbout);

		barra.getMenus().add(menuHelp);
	}

	private MenuBar crearBarraMenu() {
		// a completar Creamos la barra del menu con las distintas opciones
		MenuBar barra = new MenuBar();

		crearMenuArchivo(barra);
		crearMenuOperaciones(barra);
		crearMenuHelp(barra);
		return barra;
	}

	private void importarAgenda() throws FileNotFoundException {
		
		// a completar hacemos FileChooser para abrir una ventana y seleccionar le fichero
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File fichero = fileChooser.showOpenDialog(_stage);
		if (fichero != null) {
			
			//mostramos errpres de importacion en la caja de texto
			areaTexto.setText(Integer.toString(AgendaIO.importar(agenda, fichero)) + " Errores al cargar el fichero "
					+ fichero.getAbsolutePath());
			itemImportar.setDisable(true);
			itemExportarPersonales.setDisable(false);
		}
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
		// a completar creamos ventana con el uso de la clse Alert y DialogPane para los estilos 
		//https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.DialogPane

		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		DialogPane dialogPane = alerta.getDialogPane();

		dialogPane.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		alerta.setTitle("About agenda de contactos");
		alerta.setHeaderText("About agenda de contactos");
		alerta.setContentText("Mi agenda de contactos.");
		alerta.showAndWait();
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

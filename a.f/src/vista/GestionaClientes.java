package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestionaClientes extends JPanel {
	private JTable tabla;
	private JTextField textField;
	private AnadirCliente anadirCliente;
	private JPanel paginacion;
	private Statement statement;
	private JPanel panel;
	private JButton botonPrimero;
	private JButton botonSeparador1;
	private JButton botonPrevio1;
	private JButton botonPrevio2;
	private JButton botonPrevio3;
	private JButton botonActual;
	private JButton botonSiguiente1;
	private JButton botonSiguiente2;
	private JButton botonSiguiente3;
	private JButton botonSeparador2;
	private JButton botonUltimo;
	private JLabel labelCantidadRegistros;

	/**
	 * Create the panel.
	 */
	public GestionaClientes(Statement statement) {
		//
		this.statement = statement;
		anadirCliente = new AnadirCliente(statement);
		anadirCliente.setVisible(false);

		//
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel_5);

		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setVgap(0);
		panel_1.add(panel_6);

		JLabel lblNewLabel_1 = new JLabel("Buscar por:");
		panel_6.add(lblNewLabel_1);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Raz\u00F3n social", "Tel\u00E9fono", "E-mail", "Cuil" }));
		panel_6.add(comboBox);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setVgap(0);
		panel_1.add(panel_7);

		JLabel lblNewLabel = new JLabel("Buscar");
		panel_7.add(lblNewLabel);

		textField = new JTextField();
		panel_7.add(textField);
		textField.setColumns(15);

		JButton btnNewButton_8 = new JButton("");
		panel_7.add(btnNewButton_8);
		btnNewButton_8.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/buscar.gif")));

		JPanel panel_2 = new JPanel();
		panel_5.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton btnNewButton_1 = new JButton("A\u00F1adir");

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				anadirCliente.setVisible(true);
				anadirCliente.setLocationRelativeTo(((JButton) e.getSource()).getParent().getParent().getParent());
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/anadir.gif")));
		panel_2.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/editar.gif")));
		panel_2.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int[] filasSeleccionadas= tabla.getSelectedRows();
				for (int numeroDeFila : filasSeleccionadas) {
					try {
						statement.executeUpdate("delete from afweb_cliente where id="+tabla.getValueAt(numeroDeFila,0 ));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(filasSeleccionadas.length!=0) refrescarTabla();

			}
		});
		btnNewButton_2.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/eliminar.gif")));
		panel_2.add(btnNewButton_2);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_3, BorderLayout.NORTH);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Exportar a Excel");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Exportar.excel(tabla);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/excel.gif")));
		panel_3.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Exportar a pdf");
		btnNewButton_4.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/pdf.gif")));
		panel_3.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setToolTipText("Imprimir");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Exportar.imprimir(tabla, "AF-app - Clientes", LocalDate.now().toString(), true);
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/imprimir.gif")));
		panel_3.add(btnNewButton_5);

		paginacion = new JPanel();
		FlowLayout fl_paginacion = (FlowLayout) paginacion.getLayout();
		fl_paginacion.setHgap(1);
		fl_paginacion.setAlignment(FlowLayout.LEFT);
		panel.add(paginacion, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		// botones de la paginacion
		
		botonPrimero = new JButton("1"); // 1
		botonPrimero.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonPrimero);
		
		botonSeparador1 = new JButton("..."); //...
		botonSeparador1.setEnabled(false);
		paginacion.add(botonSeparador1);
		
		botonPrevio1 = new JButton();// 2
		botonPrevio1.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonPrevio1);
		
		botonPrevio2 = new JButton();// 3
		botonPrevio2.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonPrevio2);
		
		botonPrevio3 = new JButton();// 4
		botonPrevio3.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonPrevio3);
		
		botonActual = new JButton();// -- 5
		botonActual.setEnabled(false);
		paginacion.add(botonActual);
		
		botonSiguiente1 = new JButton();// 6
		botonSiguiente1.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonSiguiente1);
		
		botonSiguiente2 = new JButton();// 7
		botonSiguiente2.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonSiguiente2);
		
		botonSiguiente3 = new JButton();// 8
		botonSiguiente3.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonSiguiente3);
		
		botonSeparador2 = new JButton("..."); // ...
		botonSeparador2.setEnabled(false);
		paginacion.add(botonSeparador2);
		
		botonUltimo = new JButton(); // 9
		botonUltimo.addMouseListener(new EventoBotonPaginacion());
		paginacion.add(botonUltimo);
		
		// label de la paginacion
		labelCantidadRegistros = new JLabel();
		labelCantidadRegistros.setBorder(new EmptyBorder(0, 10, 0, 0));
		paginacion.add(labelCantidadRegistros);
	
		
		// pongo los botones y incribo las primeras 100 filas en la tabla
		traerDatos(1);
		ponerBotones(1);

	}

	public void ponerBotones(int posicion) {

		// obtengo la cantidad de registros
		int cantidadDeRegistros = 0;
		try {
			ResultSet resulset = statement.executeQuery("select count(*) as total from afweb_cliente");
			if (resulset.next()) cantidadDeRegistros = Integer.parseInt(resulset.getString("total"));
			resulset.close(); // cierro la conexion para ahorrar recursos
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// calculo la cantidad de botones
		double division = ((double) cantidadDeRegistros / 100); // 100 registros
		int cantidadDeBotones = (int) division; // almacena la cantidad de botones
		if (division > 1 && Integer.parseInt(Double.toString(division).split("\\.")[1]) != 0) cantidadDeBotones++; // obtengo la parte decimal y la evaluo

		
		// agrego el primer boton
		if (posicion >= 5) {
			botonPrimero.setVisible(true);
			botonSeparador1.setVisible(true);
		}else {
			botonPrimero.setVisible(false);
			botonSeparador1.setVisible(false);
		}

		// agrego los 3 botones anteriores a la posición
		if((posicion-3)>=1) {
			botonPrevio1.setText(""+(posicion-3));
			botonPrevio1.setVisible(true);
		}else {
			botonPrevio1.setVisible(false);
		}
		if((posicion-2)>=1) {
			botonPrevio2.setText(""+(posicion-2));
			botonPrevio2.setVisible(true);
		}else {
			botonPrevio2.setVisible(false);
		}
		if((posicion-1)>=1) {
			botonPrevio3.setText(""+(posicion-1));
			botonPrevio3.setVisible(true);
		}else {
			botonPrevio3.setVisible(false);
		}

		// agrego el boton actual
		botonActual.setText(""+posicion);
		botonActual.grabFocus();
		botonActual.setFocusPainted(false);
		
		// agrego los tres botones siguientes
		if((posicion)+3<=cantidadDeBotones) {
			botonSiguiente3.setText(""+(posicion+3));
			botonSiguiente3.setVisible(true);
		}else {
			botonSiguiente3.setVisible(false);
		}
		if((posicion)+2<=cantidadDeBotones) {
			botonSiguiente2.setText(""+(posicion+2));
			botonSiguiente2.setVisible(true);
		}else {
			botonSiguiente2.setVisible(false);
		}
		if((posicion)+1<=cantidadDeBotones) {
			botonSiguiente1.setText(""+(posicion+1));
			botonSiguiente1.setVisible(true);
		}else {
			botonSiguiente1.setVisible(false);
		}
		
		// agrego el utlimo boton
		if (posicion + 3 < cantidadDeBotones) {
			botonSeparador2.setVisible(true);
			botonUltimo.setText(""+cantidadDeBotones);
			botonUltimo.setVisible(true);
		}else {
			botonSeparador2.setVisible(false);
			botonUltimo.setVisible(false);
		}
		
		// cambio el texto  del label que almacena la cantidad de registros
		labelCantidadRegistros.setText(cantidadDeRegistros + " Clientes");
		
	}

	private void traerDatos(Integer numeroDePagina) {
		
		try {
			
			ResultSet resulset = statement.executeQuery("select * from afweb_cliente "); // obtengo todos los clientes
			for (int i = 0; i < (numeroDePagina*100)-100; i++) resulset.next(); // muevo el cursor 100 posiciones
//			int cantidadDeFilas=0;
//			for (int i = 0; i < 100; i++) if(resulset.next()) cantidadDeFilas++; // obtengo la cantidad de filas que siguen
//			resulset.beforeFirst(); // me posiciono antes del inicio (como viene por defecto)
			String[][] filasDeTabla = new String[100][5];
			// obtengo los siguientes 100 valores
			for (int j = 0; j < 100; j++) {
				if (resulset.next()) {
					filasDeTabla[j][0] = resulset.getString("id");
					filasDeTabla[j][1] = resulset.getString("razon_social");
					filasDeTabla[j][2] = resulset.getString("telefono");
					filasDeTabla[j][3] = resulset.getString("email");
					filasDeTabla[j][4] = resulset.getString("cuil");
				}else {
					break; // si no hay mas registros termino el bucle para no recorrelo en vano
				}
			}
			resulset.close(); // cierro el resulset para ahorrar recursos
			// añado el nuevo modelo a la tabla
			tabla.setModel(new DefaultTableModel(filasDeTabla,new String[] { "id", "Razón social", "Teléfono", "E-mail", "Cuil" }));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class EventoBotonPaginacion extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Integer numeroDePagina = Integer.parseInt(((JButton) e.getSource()).getText());
			ponerBotones(numeroDePagina);
			traerDatos(numeroDePagina);
		}
	}
	private void refrescarTabla() {
		int numeroDePagina=Integer.parseInt(botonActual.getText());
		ponerBotones(numeroDePagina);
		traerDatos(numeroDePagina);
	}

}
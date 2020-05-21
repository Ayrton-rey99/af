package vista;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.org.apache.xml.internal.security.signature.Reference;
import com.sun.pisces.PiscesRenderer;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionaClientes extends JPanel {
	private JTable tabla;
	private JTextField entradaBusqueda;
	private GuardaCliente guardaCliente;
	private JPanel paginacion;
	private Connection conexion;
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
	private DefaultTableModel modeloTabla;
	private boolean mostrandoResultadosDeBusqueda;

	/**
	 * Create the panel.
	 */
	public GestionaClientes(Connection conexion) {
		//
		this.conexion = conexion;
		guardaCliente = new GuardaCliente(conexion);
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

		JComboBox<String> busquedaPor = new JComboBox<String>();
		busquedaPor.setModel(new DefaultComboBoxModel<String>(
				new String[] { "id", "Raz\u00F3n social", "Tel\u00E9fono", "E-mail", "Cuil" }));
		panel_6.add(busquedaPor);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setVgap(0);
		panel_1.add(panel_7);

		JLabel lblNewLabel = new JLabel("Buscar");
		panel_7.add(lblNewLabel);

		entradaBusqueda = new JTextField();
		entradaBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(mostrandoResultadosDeBusqueda && entradaBusqueda.getText().equals("")) {
					refrescarTabla();
					entradaBusqueda.requestFocus();
				}
			}
		});
		panel_7.add(entradaBusqueda);
		entradaBusqueda.setColumns(15);

		JButton botonBusqueda = new JButton("");
		botonBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String buscarPor=(String)busquedaPor.getSelectedItem();
				String textoBusqueda=entradaBusqueda.getText();
			if(!(textoBusqueda.equals(""))) {
					
				
				try {
					
						ResultSet resultSet = null;
						Statement statement = null;
						switch (buscarPor) {
						case "id":
							statement=conexion.prepareStatement("SELECT * FROM afweb_cliente WHERE id ='" + textoBusqueda+"';",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							resultSet=statement.executeQuery("SELECT * FROM afweb_cliente WHERE id ='" + textoBusqueda+"';");
							break;
						case "Razón social":
							statement=conexion.prepareStatement("SELECT * FROM afweb_cliente WHERE razon_social LIKE '%"+textoBusqueda+"%';",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							resultSet=statement.executeQuery("SELECT * FROM afweb_cliente WHERE razon_social LIKE '%"+textoBusqueda+"%';");
							break;
						case "Teléfono":
							statement=conexion.prepareStatement("select * from afweb_cliente where telefono='"+textoBusqueda+"';",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							resultSet=statement.executeQuery("select * from afweb_cliente where telefono='"+textoBusqueda+"';");
							break;
						case "E-mail":
							statement=conexion.prepareStatement("SELECT * FROM afweb_cliente WHERE email LIKE '%"+textoBusqueda+"%';",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							resultSet=statement.executeQuery("SELECT * FROM afweb_cliente WHERE email LIKE '%"+textoBusqueda+"%';");
							break;
						case "Cuil":
							statement=conexion.prepareStatement("select * from afweb_cliente where cuil='" + textoBusqueda+"';",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							resultSet=statement.executeQuery("select * from afweb_cliente where cuil='" + textoBusqueda+"';");
							break;
						default:
							break;
						}
						// obtengo la cantidad de filas del resulset
						int cantidadDeFilas=0;
						while (resultSet.next()) cantidadDeFilas++;
						// me posiciono antes de la primera posicion
						resultSet.beforeFirst();
						// creo el array que se va a usar para el modelo de la tabla
						String[][] filasDeTabla = new String[cantidadDeFilas][5];
						// obtengo los siguientes valores
						for (int j = 0; j < cantidadDeFilas; j++) {
							if (resultSet.next()) {
								filasDeTabla[j][0] = resultSet.getString("id");
								filasDeTabla[j][1] = resultSet.getString("razon_social");
								filasDeTabla[j][2] = resultSet.getString("telefono");
								filasDeTabla[j][3] = resultSet.getString("email");
								filasDeTabla[j][4] = resultSet.getString("cuil");
							} 
						}
						resultSet.close();
						statement.close();
						
						// añado el nuevo modelo a la tabla
						modeloTabla= new DefaultTableModel(filasDeTabla,new String[] { "id", "Razón social", "Teléfono", "E-mail", "Cuil" }) {
							@Override
							public boolean isCellEditable(int row, int column) {
								// TODO Auto-generated method stub
								return false;
							}

						};
				
						tabla.setModel(modeloTabla);
						mostrandoResultadosDeBusqueda=true;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(GestionaClientes.this, "Completa el campo de busqueda", "Error", 2);
			}


			}
		});
		panel_7.add(botonBusqueda);
		botonBusqueda.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/buscar.gif")));

		JPanel panel_2 = new JPanel();
		panel_5.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton botonAnadir = new JButton("A\u00F1adir");

		botonAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				guardaCliente.setLocationRelativeTo(GestionaClientes.this);
				guardaCliente.anadir();
				refrescarTabla();

			}
		});
		botonAnadir.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/anadir.gif")));
		panel_2.add(botonAnadir);

		JButton botonEditar = new JButton("Editar");
		botonEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int[] filasSeleccionadas=tabla.getSelectedRows();
				if(filasSeleccionadas.length==1) {
					guardaCliente.setLocationRelativeTo(GestionaClientes.this);
					guardaCliente.actulizar(Integer.parseInt(((String)tabla.getValueAt(filasSeleccionadas[0], 0))), ((String)tabla.getValueAt(filasSeleccionadas[0], 1)), ((String)tabla.getValueAt(filasSeleccionadas[0], 2)), ((String)tabla.getValueAt(filasSeleccionadas[0], 3)), ((String)tabla.getValueAt(filasSeleccionadas[0], 4)));
					refrescarTabla();
				}else if(filasSeleccionadas.length==0) {
					JOptionPane.showMessageDialog(GestionaClientes.this, "Selecciona una fila", "Error", 2);
				}else {
					JOptionPane.showMessageDialog(GestionaClientes.this, "Selecciona solo una fila", "Error", 2);
				}
				
				
			}
		});
		botonEditar.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/editar.gif")));
		panel_2.add(botonEditar);

		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int[] filasSeleccionadas = tabla.getSelectedRows();
				if (filasSeleccionadas.length != 0) { // si hay filas seleccionadas

					for (int numeroDeFila : filasSeleccionadas) { // recorro esas filas, obtengo el id y elmino las entidades de la bd
						try {
							Statement statement = conexion.createStatement();
							statement.executeUpdate(
									"delete from afweb_cliente where id=" + tabla.getValueAt(numeroDeFila, 0));
							statement.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					for (int i = filasSeleccionadas.length - 1; i >= 0; i--) modeloTabla.removeRow(filasSeleccionadas[i]); // elmino las filas seleccionadas de la tabla
					// en el caso de que la tabla alla quedado vacia, cambio el valor del boton actual
					int valorPosicionActual=Integer.parseInt(botonActual.getText());
					if( valorPosicionActual!=1 && tabla.getRowCount()==0) botonActual.setText(""+(valorPosicionActual-1));
					refrescarTabla(); // traigo los datos de la bd
				} else {
					JOptionPane.showMessageDialog(GestionaClientes.this, "Selecciona al menos una fila", "Error", 2);
				}

			}
		});
		botonEliminar.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/eliminar.gif")));
		panel_2.add(botonEliminar);

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

		botonSeparador1 = new JButton("..."); // ...
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
			Statement statement = conexion.createStatement();
			ResultSet resulset = statement.executeQuery("select count(*) as total from afweb_cliente");
			if (resulset.next())
				cantidadDeRegistros = Integer.parseInt(resulset.getString("total"));
			statement.close();
			resulset.close(); // cierro la conexion para ahorrar recursos
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// calculo la cantidad de botones
		double division = ((double) cantidadDeRegistros / 100); // 100 registros
		int cantidadDeBotones = (int) division; // almacena la cantidad de botones
		if (division > 1 && Integer.parseInt(Double.toString(division).split("\\.")[1]) != 0)
			cantidadDeBotones++; // obtengo la parte decimal y la evaluo

		// agrego el primer boton
		if (posicion >= 5) {
			botonPrimero.setVisible(true);
			botonSeparador1.setVisible(true);
		} else {
			botonPrimero.setVisible(false);
			botonSeparador1.setVisible(false);
		}

		// agrego los 3 botones anteriores a la posición
		if ((posicion - 3) >= 1) {
			botonPrevio1.setText("" + (posicion - 3));
			botonPrevio1.setVisible(true);
		} else {
			botonPrevio1.setVisible(false);
		}
		if ((posicion - 2) >= 1) {
			botonPrevio2.setText("" + (posicion - 2));
			botonPrevio2.setVisible(true);
		} else {
			botonPrevio2.setVisible(false);
		}
		if ((posicion - 1) >= 1) {
			botonPrevio3.setText("" + (posicion - 1));
			botonPrevio3.setVisible(true);
		} else {
			botonPrevio3.setVisible(false);
		}

		// agrego el boton actual
		botonActual.setText("" + posicion);
		botonActual.grabFocus();
		botonActual.setFocusPainted(false);

		// agrego los tres botones siguientes
		if ((posicion) + 3 <= cantidadDeBotones) {
			botonSiguiente3.setText("" + (posicion + 3));
			botonSiguiente3.setVisible(true);
		} else {
			botonSiguiente3.setVisible(false);
		}
		if ((posicion) + 2 <= cantidadDeBotones) {
			botonSiguiente2.setText("" + (posicion + 2));
			botonSiguiente2.setVisible(true);
		} else {
			botonSiguiente2.setVisible(false);
		}
		if ((posicion) + 1 <= cantidadDeBotones) {
			botonSiguiente1.setText("" + (posicion + 1));
			botonSiguiente1.setVisible(true);
		} else {
			botonSiguiente1.setVisible(false);
		}

		// agrego el utlimo boton
		if (posicion + 3 < cantidadDeBotones) {
			botonSeparador2.setVisible(true);
			botonUltimo.setText("" + cantidadDeBotones);
			botonUltimo.setVisible(true);
		} else {
			botonSeparador2.setVisible(false);
			botonUltimo.setVisible(false);
		}

		// cambio el texto del label que almacena la cantidad de registros
		labelCantidadRegistros.setText(cantidadDeRegistros + " Clientes");

	}

	public void traerDatos(Integer numeroDePagina) {
		mostrandoResultadosDeBusqueda=false;
		try {
			Statement statement = conexion.prepareStatement("select * from afweb_cliente",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resulset = statement.executeQuery("select * from afweb_cliente "); // obtengo todos los clientes

			resulset.absolute((numeroDePagina * 100) - 100);// muevo el cursor 100 posiciones
			int cantidadDeFilas = 0;
			for (int i = 0; i < 100; i++) {
				if (resulset.next()) {
					cantidadDeFilas++; // obtengo la cantidad de filas que siguen
				} else {
					break; // termino el bucle si no hay mas filas
				}
			}
			resulset.beforeFirst(); // me posiciono antes del inicio (como viene por defecto)
			resulset.absolute((numeroDePagina * 100) - 100);// muevo el cursor 100 posiciones
			String[][] filasDeTabla = new String[cantidadDeFilas][5];
			// obtengo los siguientes valores
			for (int j = 0; j < cantidadDeFilas; j++) {
				if (resulset.next()) {
					filasDeTabla[j][0] = resulset.getString("id");
					filasDeTabla[j][1] = resulset.getString("razon_social");
					filasDeTabla[j][2] = resulset.getString("telefono");
					filasDeTabla[j][3] = resulset.getString("email");
					filasDeTabla[j][4] = resulset.getString("cuil");
				} 
			}
			statement.close();
			resulset.close(); // cierro el resulset para ahorrar recursos
			// añado el nuevo modelo a la tabla
			modeloTabla= new DefaultTableModel(filasDeTabla,new String[] { "id", "Razón social", "Teléfono", "E-mail", "Cuil" }) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}

			};
	
			tabla.setModel(modeloTabla);

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
		int numeroDePagina = Integer.parseInt(botonActual.getText());
		traerDatos(numeroDePagina);
		ponerBotones(numeroDePagina);
	}

}
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
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestionaClientes extends JPanel {
	private JTable tabla;
	private JTextField textField;
	private AnadirCliente anadirCliente;
	private JPanel paginacion;

	/**
	 * Create the panel.
	 */
	public GestionaClientes(Statement statement) {
		//
		anadirCliente=new AnadirCliente(statement);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Raz\u00F3n social", "Tel\u00E9fono", "E-mail", "Cuil"}));
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
				anadirCliente.setLocationRelativeTo( ((JButton)e.getSource()).getParent().getParent().getParent());
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/anadir.gif")));
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/editar.gif")));
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/eliminar.gif")));
		panel_2.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_3, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("");
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
		btnNewButton_4.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/pdf.gif")));
		panel_3.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
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
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tabla);
		
		// pongo los botones
		ponerBotones(statement,1);
		
	}
	public void ponerBotones(Statement statement, int posicion) {
		
		// quito los botones si es que los hay
		if(paginacion.getComponentCount()!=0) {
			paginacion.removeAll();
		}
		
		// obtengo la cantidad de registros
		int cantidadDeRegistros=0;
		try {
			ResultSet resultado= statement.executeQuery("select count(*) as total from afweb_cliente");
			if(resultado.next()) {
				cantidadDeRegistros=Integer.parseInt( resultado.getString("total"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// calculo la cantidad de botones
		int cantidadDeBotones=0; // almacena la cantidad de botones
		double division=((double)cantidadDeRegistros/100); // 100 registros
		
		if(division>1) {
			int parteDecimal=Integer.parseInt(Double.toString(division).split("\\.")[1] );
			if(parteDecimal==0) {
				cantidadDeBotones=(int)division;
			}else {
				division++;
				cantidadDeBotones=(int)division;
			}
			
		}
		// array que almacena los botones
		ArrayList<JButton> botones=new ArrayList<JButton>();
		
		// agrego el primer boton
		if(posicion>=5) {
			JButton primerBoton=new JButton("1");
			primerBoton.addMouseListener(new EventoBotonPaginacion(statement));
			botones.add(primerBoton);
			JButton separador=new JButton("...");
			separador.setEnabled(false);
			botones.add(separador);
		}
		
		// agrego los 3 botones anteriores a la posición 
		for (int i = posicion-3; i < posicion; i++) {
			if(i<1) continue;
			JButton boton=new JButton(""+i);
			boton.addMouseListener(new EventoBotonPaginacion(statement));
			botones.add(boton);
		}
		
		// agrego el boton actual
		JButton botonPosicionActual=new JButton(""+posicion);
		botonPosicionActual.setEnabled(false);
		botones.add(botonPosicionActual);
		
		// agrego los tres botones siguientes
		for (int i = posicion+1; i <= posicion+3; i++) {
			if(i>cantidadDeBotones) break;
			JButton boton=new JButton(""+i);
			boton.addMouseListener(new EventoBotonPaginacion(statement));
			botones.add(boton);
		}
		//agrego el utlimo boton
		if(posicion+3<cantidadDeBotones) {
			JButton separador=new JButton("...");
			separador.setEnabled(false);
			botones.add(separador);
			JButton ultimoBoton=new JButton(""+cantidadDeBotones);
			ultimoBoton.addMouseListener(new EventoBotonPaginacion(statement));
			botones.add(ultimoBoton);
		}
		
	
		// pongo los botones
		for (JButton jButton : botones) {
			paginacion.add(jButton);
		}
		// añado el label con la cantidad de registros
		JLabel labelCantidadRegistros=new JLabel(cantidadDeRegistros+" Clientes");
		labelCantidadRegistros.setBorder(new EmptyBorder(0, 10, 0, 0));
		paginacion.add(labelCantidadRegistros);
		
		// refresco el panel para poder visualizar los cambios
		paginacion.setVisible(false);
		paginacion.setVisible(true);
	}
	class EventoBotonPaginacion extends MouseAdapter{
		private Statement statement;
		public EventoBotonPaginacion(Statement statement) {
			// TODO Auto-generated constructor stub
			this.statement=statement;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			ponerBotones(statement, Integer.parseInt(((JButton)e.getSource()).getText()));
		}
	}

}













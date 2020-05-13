package vista;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

public class GestionaClientes extends JPanel {
	private JTextField campoDeBusqueda;
	private AnadirCliente anadirCliente;
	private JTable tabla;
	/**
	 * Create the panel.
	 */
	public GestionaClientes(Statement declaracion) {
//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_B) {
//					System.out.println("ctrl B");
//					
//				}
//			}
//		});
		
		// ventana para añadir un cliente
		anadirCliente=new AnadirCliente(declaracion);
		anadirCliente.setLocationRelativeTo(this);
		anadirCliente.setVisible(false);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		FlowLayout fl_panel = new FlowLayout();
		fl_panel.setHgap(0);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		
		JPanel panelDeFiltro = new JPanel();
		panelDeFiltro.setBorder(null);
		panel.add(panelDeFiltro);
		panelDeFiltro.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		JLabel textoBuscarPor = new JLabel("Buscar por: ");
		panelDeFiltro.add(textoBuscarPor);
		
		JComboBox<String> filtroDeBusqueda = new JComboBox<String>();
		panelDeFiltro.add(filtroDeBusqueda);
		filtroDeBusqueda.setModel(new DefaultComboBoxModel<String>(new String[] {"Razon Social", "Telefono", "E-mail", "Cuil"}));
		
		JPanel panelDeBusqueda = new JPanel();
		panel.add(panelDeBusqueda);
		panelDeBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		JLabel textoBuscar = new JLabel("Buscar:");
		panelDeBusqueda.add(textoBuscar);

		
		campoDeBusqueda = new JTextField();
		panelDeBusqueda.add(campoDeBusqueda);
		campoDeBusqueda.setColumns(15);
		
		JButton botonBuscar = new JButton("Buscar");
		panelDeBusqueda.add(botonBuscar);
		
			botonBuscar.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/buscar.gif")));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		add(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelDeOperaciones = new JPanel();
		panelDeOperaciones.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panelDeOperaciones = (FlowLayout) panelDeOperaciones.getLayout();
		fl_panelDeOperaciones.setAlignment(FlowLayout.LEFT);
		panelPrincipal.add(panelDeOperaciones, BorderLayout.NORTH);
		
		JButton botonAnadir = new JButton("A\u00F1adir");
		botonAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!anadirCliente.isVisible()) anadirCliente.restablecerFormulario();
				anadirCliente.setVisible(true);
			}
		});
		botonAnadir.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/anadir.gif")));
		panelDeOperaciones.add(botonAnadir);
		
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		botonEliminar.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/eliminar.gif")));
		panelDeOperaciones.add(botonEliminar);
		
		JButton botonEditar = new JButton("Editar");
		botonEditar.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/editar.gif")));
		panelDeOperaciones.add(botonEditar);
		
		JPanel contenedorDeTabla = new JPanel();
		contenedorDeTabla.setBorder(new TitledBorder(null, "Tabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPrincipal.add(contenedorDeTabla, BorderLayout.CENTER);
		contenedorDeTabla.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new EmptyBorder(5, 5, 5, 5));
		contenedorDeTabla.add(panelTabla, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollDeTabla = new JScrollPane();
		panelTabla.add(scrollDeTabla, BorderLayout.CENTER);
		
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
				{"Batman", "2131213", "batibati@gmail.com", "316516"},
				{"Robin", "165165", "robinbin@gmail.com", "64165165"},
				{"sponja", "465465", "bodesponjado@gmail.com", "1"},
			},
			new String[] {
				"Razon Social", "Telefono", "E-mail", "Cuil"
			}
		));
		
		scrollDeTabla.setViewportView(tabla);
		
		JPanel panelExportar = new JPanel();
		panelTabla.add(panelExportar, BorderLayout.NORTH);
		FlowLayout fl_panelExportar = (FlowLayout) panelExportar.getLayout();
		fl_panelExportar.setAlignment(FlowLayout.RIGHT);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/excel.gif")));
		panelExportar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/pdf.gif")));
		panelExportar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/imprimir.gif")));
		panelExportar.add(btnNewButton_2);
		
				JPanel paginacion = new JPanel();
				panelTabla.add(paginacion, BorderLayout.SOUTH);
				FlowLayout fl_paginacion = (FlowLayout) paginacion.getLayout();
				fl_paginacion.setVgap(3);
				fl_paginacion.setHgap(0);
				fl_paginacion.setAlignment(FlowLayout.LEFT);
				
				JButton atras = new JButton("<");
				paginacion.add(atras);
				
				JButton num1 = new JButton("1");
				paginacion.add(num1);
				
				JButton num2 = new JButton("2");
				paginacion.add(num2);
				
				JButton adelante = new JButton(">");
				paginacion.add(adelante);
		
		
	}
	public void limpiarTabla() { // final: Si lo aplicamos a un método, no se permite que subclases redefinan el método.
		tabla.setModel(new DefaultTableModel());
	}


}






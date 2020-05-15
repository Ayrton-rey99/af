package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Statement;
import java.time.LocalDate;

public class GestionaClientes extends JPanel {
	private JTable tabla;
	private JTextField textField;
	private AnadirCliente anadirCliente;

	/**
	 * Create the panel.
	 */
	public GestionaClientes(Statement statement) {
		//
		anadirCliente=new AnadirCliente(statement);
		anadirCliente.setVisible(false);
		
		//
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Buscar");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(15);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setIcon(new ImageIcon(GestionaClientes.class.getResource("/imagenes/buscar.gif")));
		panel_1.add(btnNewButton_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnNewButton_1 = new JButton("A\u00F1adir");
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				anadirCliente.setVisible(true);
				anadirCliente.setLocationRelativeTo( ((JButton)e.getSource()).getParent().getParent());
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
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnNewButton_6 = new JButton("Anterior");
		panel_4.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Siguiente");
		panel_4.add(btnNewButton_7);
		
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

	}

}

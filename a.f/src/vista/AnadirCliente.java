package vista;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;

public class AnadirCliente extends JFrame {

	private JPanel contentPane;
	private JTextField entradaRazonSocial;
	private JTextField entradaTelefono;
	private JTextField entradaCorreo;
	private JTextField entradaCuil;
	private JLabel labelRazonSocial;
	private JLabel labelCorreo;
	private JLabel labelTelefono;
	private JLabel labelCuil;
	private JLabel labelGuardado;




	/**
	 * Create the frame.
	 */
	public AnadirCliente(Statement statement) {
		setTitle("A\u00F1adir Cliente");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				borrarDatos();
			}
		});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(360, 360);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Entidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		panel.add(lblNewLabel);
		
		JPanel campos = new JPanel();
		campos.setBorder(new TitledBorder(null, "Campos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(campos, BorderLayout.CENTER);
		campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		campos.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_4.add(panel_1, BorderLayout.WEST);
		
		labelRazonSocial = new JLabel("Raz\u00F3n social");
		panel_1.add(labelRazonSocial);
		labelRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9, BorderLayout.EAST);
		
		entradaRazonSocial = new JTextField();
		panel_9.add(entradaRazonSocial);
		entradaRazonSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ocultarGuardado();
				limitarTamanoA30(e);
			}
		});
		entradaRazonSocial.setColumns(15);
		
		JPanel panel_3 = new JPanel();
		campos.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10, BorderLayout.WEST);
		
		labelTelefono = new JLabel("Tel\u00E9fono");
		panel_10.add(labelTelefono);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, BorderLayout.EAST);
		
		entradaTelefono = new JTextField();
		panel_11.add(entradaTelefono);
		entradaTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ocultarGuardado();
				limitarTamanoA30(e);
			}
		});
		entradaTelefono.setColumns(15);
		
		JPanel panel_5 = new JPanel();
		campos.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout fl_panel_7 = (FlowLayout) panel_7.getLayout();
		panel_5.add(panel_7, BorderLayout.WEST);
		
		labelCorreo = new JLabel("E-mail");
		panel_7.add(labelCorreo);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.EAST);
		
		entradaCorreo = new JTextField();
		panel_8.add(entradaCorreo);
		entradaCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ocultarGuardado();
				limitarTamanoA30(e);
			}
		});
		entradaCorreo.setColumns(15);
		
		JPanel panel_6 = new JPanel();
		campos.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12, BorderLayout.WEST);
		
		labelCuil = new JLabel("Cuil");
		panel_12.add(labelCuil);
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13, BorderLayout.EAST);
		
		entradaCuil = new JTextField();
		panel_13.add(entradaCuil);
		entradaCuil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ocultarGuardado();
				limitarTamanoA30(e);
			}
		});
		entradaCuil.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_2.setBorder(new TitledBorder(null, "Operaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton guardar = new JButton("Guardar");
		guardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				guardarDatos(statement);
			}
		});
		
		labelGuardado = new JLabel("\u00A1Guardado!");
		labelGuardado.setVisible(false);
		labelGuardado.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelGuardado.setForeground(new Color(0, 128, 0));
		panel_2.add(labelGuardado);
		guardar.setIcon(new ImageIcon(AnadirCliente.class.getResource("/imagenes/anadirCliente.gif")));
		panel_2.add(guardar);
	}
	
	
	private void guardarDatos(Statement statement) {
		// guardando datos
		String textoTelefono= entradaTelefono.getText();
		String textoCuil=entradaCuil.getText();
		String textoEmail=entradaCorreo.getText();
		String textoRazonSocial=entradaRazonSocial.getText();
		
		// Validando Razon Social
		boolean razonSocialCorrecta=false;
		if(!textoRazonSocial.isEmpty() && textoRazonSocial.length()>=3) razonSocialCorrecta=true;
		
		// Validando el cuil
		boolean cuilCorrecto=false;
		if (Pattern.compile("[0-9]{4,}").matcher(textoCuil).matches() || textoCuil.isEmpty()) cuilCorrecto=true;
		
		// Validando el telefono
		boolean telefonoCorrecto=false;
		if (Pattern.compile("[0-9 || +]{4,}").matcher(textoTelefono).matches() || textoTelefono.isEmpty()) telefonoCorrecto=true;
		
		// Validando correo
		boolean emailCorrecto=false;
		if (Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(textoEmail).matches()) {
			emailCorrecto=true;
			textoEmail="'"+textoEmail+"'";
		}else if(textoEmail.isEmpty()) {
			emailCorrecto=true;
			textoEmail="null";
		}
		
		if(telefonoCorrecto && cuilCorrecto && emailCorrecto && razonSocialCorrecta) {
			try {
				statement.executeUpdate("INSERT INTO afweb_cliente values(null,'" +textoRazonSocial+ "','" +textoTelefono+ "'," + textoEmail + ",'" +textoCuil+ "')");
				
				labelGuardado.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,"El cliente \""+textoRazonSocial+"\" ya esta registrado.");
				//e.printStackTrace();
			}
			
			borrarDatos();
		}else {
			// cambio los colores de los label en caso de que  algun dato sea incorrecto
			
			if(!telefonoCorrecto) {
				labelTelefono.setForeground(Color.RED);
				labelTelefono.setText("Teléfono (incorrecto)");
			}else {
				labelTelefono.setText("Teléfono");
				labelTelefono.setForeground(Color.BLACK);
				
			}
			if(!cuilCorrecto) {
				labelCuil.setForeground(Color.RED);
				labelCuil.setText("cuil (incorrecto)");
			}else {
				labelCuil.setText("cuil");
				labelCuil.setForeground(Color.BLACK);
			}
			if(!emailCorrecto) {
				labelCorreo.setForeground(Color.RED);
				labelCorreo.setText("E-mail (incorrecto)");
			}else {
				labelCorreo.setText("E-mail");
				labelCorreo.setForeground(Color.BLACK);
			}
			if(!razonSocialCorrecta) {
				labelRazonSocial.setForeground(Color.RED);
				labelRazonSocial.setText("Razón social (incorrecto)");
			}else {
				labelRazonSocial.setText("Razón social");
				labelRazonSocial.setForeground(Color.BLACK);
			}
		}

		
		
	}
	private void ocultarGuardado() {
		if(labelGuardado.isVisible()) labelGuardado.setVisible(false);
	}
	private void limitarTamanoA30(KeyEvent e) {
		if(((JTextField)e.getSource()).getText().length()==30) e.consume();
	}
	private void borrarDatos() {

		// borro los input
		entradaCorreo.setText("");
		entradaCuil.setText("");
		entradaRazonSocial.setText("");
		entradaTelefono.setText("");
		// restablezco los label
		labelTelefono.setText("Teléfono");
		labelCuil.setText("cuil");
		labelCorreo.setText("E-mail");
		labelRazonSocial.setText("Razón social");
		// cambio los colores nuevamente
		labelTelefono.setForeground(Color.BLACK);
		labelCuil.setForeground(Color.BLACK);
		labelCorreo.setForeground(Color.BLACK);
		labelRazonSocial.setForeground(Color.BLACK);
		
		
	}


}










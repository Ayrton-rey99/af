package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import conexionBD.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AnadirCliente extends JFrame {

	private JPanel contentPane;
	private JTextField entradaRazonSocial, entradaTelefono, entradaEmail, entradaCuil;
	private JLabel guardado;
	private JLabel labelRazonSocial, labelTelefono, labelEmail, labelCuil;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AnadirCliente(Statement declaracion) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
			}
		});
		setSize(450,300);
		setTitle("Añadir Cliente");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		JLabel Titulo = new JLabel("A\u00F1adir Cliente");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelTitulo.add(Titulo);
		
		JPanel panelAnadir = new JPanel();
		FlowLayout fl_panelAnadir = (FlowLayout) panelAnadir.getLayout();
		fl_panelAnadir.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelAnadir, BorderLayout.SOUTH);
		
		JButton anadir = new JButton("A\u00F1adir");
		anadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					
					// guardando datos
					String textoTelefono= entradaTelefono.getText();
					String textoCuil=entradaCuil.getText();
					String textoEmail=entradaEmail.getText();
					String textoRazonSocial=entradaRazonSocial.getText();
					
					// Validando Razon Social
					boolean razonSocialCorrecta=false;
					if(!textoRazonSocial.isEmpty() && textoRazonSocial.length()>=3) razonSocialCorrecta=true;
					
					// Validando el cuil
					Pattern validaCuil=Pattern.compile("[0-9]{4,}");
					boolean cuilCorrecto=false;
					if (validaCuil.matcher(textoCuil).matches() || textoCuil.isEmpty()) cuilCorrecto=true;
					
					// Validando el telefono
					Pattern validaTelefono=Pattern.compile("[0-9 || +]{4,}");
					boolean telefonoCorrecto=false;
					if (validaTelefono.matcher(textoTelefono).matches() || textoTelefono.isEmpty()) telefonoCorrecto=true;
					
					// Validando correo
					Pattern validaEmail=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
					boolean emailCorrecto=false;
					if (validaEmail.matcher(textoEmail).matches()) emailCorrecto=true;
					if(textoEmail.isEmpty()) {
						emailCorrecto=true;
						textoEmail="null";
					}else {
						textoEmail="'"+textoEmail+"'";
					}
					
					
					if(telefonoCorrecto && cuilCorrecto && emailCorrecto && razonSocialCorrecta) {
						declaracion.executeUpdate("INSERT INTO afweb_cliente values(null,'" +textoRazonSocial+ "','" +textoTelefono+ "'," + textoEmail + ",'" +textoCuil+ "')");
						// una vez enviado borro los textField, restablezco el color a negro de los label y pongo visible el texto "Guardado!"
						guardado.setVisible(true);
						entradaRazonSocial.setText("");
						entradaTelefono.setText("");
						entradaEmail.setText("");
						entradaCuil.setText("");
						labelRazonSocial.setForeground(Color.BLACK);
						labelCuil.setForeground(Color.BLACK);
						labelEmail.setForeground(Color.BLACK);
						labelTelefono.setForeground(Color.BLACK);
						entradaRazonSocial.requestFocus();
					}
					// cambio los colores de los label en caso de que  algun dato sea incorrecto
					if(!telefonoCorrecto) labelTelefono.setForeground(Color.RED);
					if(!cuilCorrecto) labelCuil.setForeground(Color.RED);
					if(!emailCorrecto) labelEmail.setForeground(Color.RED);
					if(!razonSocialCorrecta) labelRazonSocial.setForeground(Color.RED);
					
					if(!telefonoCorrecto) {
						JOptionPane.showMessageDialog(e.getComponent().getParent().getParent().getParent(), "¡El numero telefónico es incorrecto!", "Error", 3, new ImageIcon(this.getClass().getResource("/imagenes/telefonoIncorrecto.gif")));	
					}
					if(!cuilCorrecto) {
						JOptionPane.showMessageDialog(e.getComponent().getParent().getParent().getParent(), "¡El cuil es incorrecto!", "Error", 3, new ImageIcon(this.getClass().getResource("/imagenes/cuilIncorrecto.gif")));
					}
					if(!emailCorrecto) {
						JOptionPane.showMessageDialog(e.getComponent().getParent().getParent().getParent(), "¡El E-mail es incorrecto!", "Error", 3, new ImageIcon(this.getClass().getResource("/imagenes/emailIncorrecto.gif")));
					}
					if(!razonSocialCorrecta) {
						JOptionPane.showMessageDialog(e.getComponent().getParent().getParent().getParent(), "¡Por favor introduce una razón social!", "Error", 3, new ImageIcon(this.getClass().getResource("/imagenes/error.gif")));
					}
		
				} catch (SQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(e.getComponent().getParent().getParent().getParent(), "¡El cliente ya se encuentra registrado!", "Error", 3, new ImageIcon(this.getClass().getResource("/imagenes/error.gif")));
				
				}catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		guardado = new JLabel("Guardado!");
		guardado.setVisible(false);
		guardado.setFont(new Font("Tahoma", Font.BOLD, 15));
		guardado.setForeground(Color.GREEN);
		panelAnadir.add(guardado);
		anadir.setIcon(new ImageIcon(AnadirCliente.class.getResource("/imagenes/anadirCliente.gif")));
		panelAnadir.add(anadir);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "A\u00F1adir", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 4, 2));
		
		labelRazonSocial = new JLabel("Raz\u00F3n social");
		labelRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(labelRazonSocial);
		
		entradaRazonSocial = new JTextField();
		entradaRazonSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(entradaRazonSocial.getText().length()==30) e.consume();
				// oculto el texto Guardado! en caso de que este visible
				if(guardado.isVisible()) guardado.setVisible(false);
			}
		});
		panel_3.add(entradaRazonSocial);
		entradaRazonSocial.setColumns(10);
		
		labelTelefono = new JLabel("Telefono");
		labelTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(labelTelefono);
		
		entradaTelefono = new JTextField();
		entradaTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(entradaRazonSocial.getText().length()==30) e.consume();
				// oculto el texto Guardado! en caso de que este visible
				if(guardado.isVisible()) guardado.setVisible(false);
			}
		});
		panel_3.add(entradaTelefono);
		entradaTelefono.setColumns(10);
		
		labelEmail = new JLabel("E-mail");
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(labelEmail);
		
		entradaEmail = new JTextField();
		entradaEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// oculto el texto Guardado! en caso de que este visible
				if(guardado.isVisible()) guardado.setVisible(false);
			}
		});
		panel_3.add(entradaEmail);
		entradaEmail.setColumns(10);
		
		labelCuil = new JLabel("Cuil");
		labelCuil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(labelCuil);
		
		entradaCuil = new JTextField();
		entradaCuil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(entradaRazonSocial.getText().length()==30) e.consume();
				// oculto el texto Guardado! en caso de que este visible
				if(guardado.isVisible()) guardado.setVisible(false);
			}
		});
		panel_3.add(entradaCuil);
		entradaCuil.setColumns(10);
	}
	public void restablecerFormulario() {
		entradaRazonSocial.setText("");
		entradaTelefono.setText("");
		entradaEmail.setText("");
		entradaCuil.setText("");
		entradaRazonSocial.requestFocus();
		labelRazonSocial.setForeground(Color.BLACK);
		labelCuil.setForeground(Color.BLACK);
		labelEmail.setForeground(Color.BLACK);
		labelTelefono.setForeground(Color.BLACK);
		guardado.setVisible(false);
	}


}

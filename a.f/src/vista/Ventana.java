package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import conexionBD.Conexion;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private GestionaClientes gestionaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");// com.jtattoo.plaf.mint.MintLookAndFeel

        } catch (ClassNotFoundException ex1) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
        } catch (InstantiationException ex2) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex2);
        } catch (IllegalAccessException ex3) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex3);
        } catch (javax.swing.UnsupportedLookAndFeelException ex4) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex4);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		//
		Conexion conexion=new Conexion();
		Statement statement= conexion.getDeclaracion();
		//

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 600);
		setMinimumSize(new Dimension(760,600));
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Mis clientes");
		mnNewMenu.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/misClientes.gif")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Clientes");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gestionaClientes.setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/clientes.gif")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Puntos de entrega");
		mntmNewMenuItem_1.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/puntosDeEntrega.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// gestionadores
		gestionaClientes=new GestionaClientes(statement);
		gestionaClientes.setVisible(false);
		contentPane.add(gestionaClientes);
		//
		
	}

}

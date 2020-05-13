package vista;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.UIManager;
import conexionBD.Conexion;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class Ventana extends JFrame {
	private GestionaClientes gestionaClientes;
	

	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

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

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// ventana
					Ventana frame = new Ventana();
					frame.setVisible(true);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});



	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		// conexion a la base de datos
		Conexion conexion=new Conexion();
		Statement declaracion= conexion.getDeclaracion();
		
		// Ventana
		setTitle("AFapp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 600);
		setMinimumSize(new Dimension(760,600));
		setLocationRelativeTo(null);
		
		
		// Gestionadores
		gestionaClientes=new GestionaClientes(declaracion);
		add(gestionaClientes);
		gestionaClientes.setVisible(false);
	
		// Menu principal
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		// Menu opClientes
		JMenu opClientes = new JMenu("OP clientes");
		opClientes.setMnemonic('c');
		opClientes.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/opClientes.gif")));
		menu.add(opClientes);
		
		// Item clientes
		JMenuItem clientes = new JMenuItem("Clientes");
		clientes.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/clientes.gif")));
		opClientes.add(clientes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Puntos de entrega");
		mntmNewMenuItem.setIcon(new ImageIcon(Ventana.class.getResource("/imagenes/puntosDeEntrega.png")));
		opClientes.add(mntmNewMenuItem);
		// Evento item clientes 
		clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gestionaClientes.isVisible()) {
					gestionaClientes.setVisible(true);
				}
				
			}
		});
		
	}

}

















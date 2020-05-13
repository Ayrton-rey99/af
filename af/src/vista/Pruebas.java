package vista;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pattern validaTelefono=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		String textoTelefono= JOptionPane.showInputDialog("prueba");
	System.out.println(validaTelefono.matcher(textoTelefono).matches());
	}

}

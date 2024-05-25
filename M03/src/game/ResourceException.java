package game;

import javax.swing.JOptionPane;

public class ResourceException extends Exception{
	public ResourceException(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Not enough resources", JOptionPane.WARNING_MESSAGE);
	}
}



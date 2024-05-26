package game;

import javax.swing.JOptionPane;

public class EmptyTextException extends Exception{
	public EmptyTextException(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Empty text", JOptionPane.WARNING_MESSAGE);
	}
}

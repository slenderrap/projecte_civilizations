package game;

import javax.swing.JOptionPane;

public class BuildingException extends Exception{
	public BuildingException(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Not enough buildings", JOptionPane.WARNING_MESSAGE);
	}
}
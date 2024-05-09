package front;

import java.awt.Font;
import java.io.InputStream;

public interface Fuente {
	default Font FuenteEnchantedLand() {
		try {
			InputStream is = getClass().getResourceAsStream("src\front\\fuente\\Salium.ttf");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
			return customFont;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

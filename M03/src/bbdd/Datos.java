package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Datos {
	public static void main(String[] args) throws Exception {
//		String urlDatos = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "civil";
		String password = "civil";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(urlDatos, user, password);
		String query = "SELECT * FROM CIVILIZATION_STATS";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

		System.out.println(rs.next());

		while (rs.next()) {
			System.out.println("aqui");
			System.out.println(rs.getInt(1) + "  " + rs.getString(2));

		}
	}
}

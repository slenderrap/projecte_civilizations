package bbdd;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Datos {
	public static void main(String[] args) throws Exception{
		String urlDatos="jdbc:oracle:thin:@192.168.56.1:1521:orcl";
		String user = "civil";
		String password = "civil";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =  DriverManager.getConnection(urlDatos,user,password);
		Statement st =  conn.createStatement();
		String query="Show tables";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getString(1));
			
		}
	}
}

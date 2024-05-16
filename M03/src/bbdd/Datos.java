package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//
//public class Datos {
//	private String urlDatos="jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false";
//	private	String user = "civil";
//	private String password = "civil";
//	private Connection conn;
//	public Datos() {
//		
//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		this.conn =  DriverManager.getConnection(this.urlDatos,this.user,this.password);
//		
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public int crearNuevaPartida(String name) {
//		try {
//			System.out.println(name);
//			String insert = "Insert into Civilization_stats(\"name\") values(?)";
//			System.out.println("antes st");	
//			
//			PreparedStatement ps = conn.prepareStatement(insert,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			ps.setString(1, name);
//			ps.executeUpdate();
//			
//			return maxID();
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return 0;
//		}
//		
//	}
//	
//	public int maxID() {
//		try {
//			String query = "Select max(id_civilization) from Civilization_stats";
//			Statement st =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
//			ResultSet rs = st.executeQuery(query);
//			rs.next();
//			int id =rs.getInt(1);
//			return id;
//		} catch (Exception e) {
//			return 0;
//		}
//			
//	}
//	
//}
	

	
	
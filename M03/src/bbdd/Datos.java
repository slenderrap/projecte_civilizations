package bbdd;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Datos {
//	public static void main(String[] args) throws Exception{
//		//en '@' poner la ip donde se ejecuta sql developer,  
//		String urlDatos="jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false";
//		String user = "civil";
//		String password = "civil";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection conn =  DriverManager.getConnection(urlDatos,user,password);
//		String query="SELECT * FROM CIVILIZATION_STATS";
//		Statement st =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//		ResultSet rs = st.executeQuery(query);
//		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
//		String insert = "insert into Civilization_stats(\"name\" )"; 
//		PreparedStatement ps = conn.prepareStatement(insert,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//		
//		while (rs.next()) {
//			System.out.println("aqui");
//			System.out.println(rs.getInt(1)+"  "+rs.getString(2));
//			
//		}
//	}
}

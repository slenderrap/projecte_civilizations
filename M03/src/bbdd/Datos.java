package bbdd;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Datos {
	public static void main(String[] args) throws Exception{
		//en '@' poner la ip donde se ejecuta sql developer,  
		String urlDatos="jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false";
		String user = "civil";
		String password = "civil";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =  DriverManager.getConnection(urlDatos,user,password);
		String query="SELECT * FROM CIVILIZATION_STATS order by id_civilization";
		query="select max(id_civilization) from civilization_stats";
		Statement st =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		String insert = "insert into Civilization_stats(\"name\") values (?)"; 
		String delete = "delete from Civilization_stats where id_civilization = (?)";
		PreparedStatement ps = conn.prepareStatement(delete,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		// query insert ps.setString(1, "prueba3");
		
		
		rs = st.executeQuery(query);
		rs.next();
		int id = rs.getInt(1);
		System.out.println(id);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		System.out.println("**\nejecutado\n**");
		query="SELECT * FROM CIVILIZATION_STATS order by id_civilization";
		rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(
					rsmd.getColumnName(1)+": " + rs.getInt(1)+"\n"+rsmd.getColumnName(2)+": " + rs.getString(2)+"\n"+rsmd.getColumnName(3)+": " + rs.getInt(3)+"\n"+
					rsmd.getColumnName(4)+": " +rs.getInt(4)+"\n"+rsmd.getColumnName(5)+": " + rs.getInt(5)+"\n"+rsmd.getColumnName(6)+": " + rs.getInt(6)+"\n"+
					rsmd.getColumnName(7)+": " +rs.getInt(7)+"\n"+rsmd.getColumnName(8)+": " + rs.getInt(8)+"\n"+ rsmd.getColumnName(9)+": " + rs.getInt(9)+"\n"+
					rsmd.getColumnName(10)+": " + rs.getInt(10)+"\n"+rsmd.getColumnName(11)+": " + rs.getInt(11)+"\n"+rsmd.getColumnName(12)+": " + rs.getInt(12)+"\n"+
					rsmd.getColumnName(13)+": " + rs.getInt(13)+"\n"+rsmd.getColumnName(14)+": " + rs.getInt(14)+"\n"
					);
			
		}
	}
}

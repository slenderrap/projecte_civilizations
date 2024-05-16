package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Datos {
	private String urlDatos="jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private	String user = "civil";
	private String password = "civil";
	private Connection conn;
	public Datos() {
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		this.conn =  DriverManager.getConnection(this.urlDatos,this.user,this.password);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int crearNuevaPartida(String name) {
		try {
			System.out.println(name);
			String insert = "Insert into Civilization_stats(\"name\") values(?)";
			
			PreparedStatement ps = conn.prepareStatement(insert,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, name);
			ps.executeUpdate();
			
			return maxID();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int maxID() {
		try {
			String query = "Select max(id_civilization) from Civilization_stats";
			Statement st =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			ResultSet rs = st.executeQuery(query);
			rs.next();
			int id =rs.getInt(1);
			return id;
		} catch (Exception e) {
			return 0;
		}
			
	}
	
	public ArrayList<String[]> mostrarPartidasNombre(String name) {
		ArrayList<String[]> resultados = new ArrayList<>();  
		
		try {
			String search = "select id_civilization,\"name\",battles_counter from civilization_stats where lower(\"name\") like lower('%"+name+"%')";
			Statement st = conn.createStatement();
			String[] fila = new String[3];  
			ResultSet rs  = st.executeQuery(search);
			while (rs.next()) {
				fila[0]= String.valueOf(rs.getInt(1));
				fila[1]= rs.getString(2);
				fila[2]= String.valueOf(rs.getInt(3));
				System.out.println(fila.toString());
				resultados.add(fila);
				System.out.println("fila numero"+resultados.size()+": "+fila[0]+fila[1]+fila[2]);
			}
			
			System.out.println(resultados.toString());
			return resultados;
		} catch (Exception e) {
			System.out.println("error!!!!");
			return resultados ;
		}

	}
	
	public ArrayList<String> seleccionarPartida(int id) {
		ArrayList<String> partida = new ArrayList<String>();
		try {
			String search = "select * from civilization_stats where id_civilization = "+id;
			Statement st = conn.createStatement();
			ResultSet rs  = st.executeQuery(search);
			rs.next();
			for (int i=1;i<10;i++) {
				if (partida.size()!=1) {
					partida.add(String.valueOf(rs.getInt(i)));
				}else {
					partida.add(rs.getString(i));

				}
			}

			return partida;
		} catch (Exception e) {
			System.out.println("*******\nError!\nEl ID "+id+" no existe!\n*******");
			return partida;
			
		}
		
	}
	
	

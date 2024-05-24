package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import game.MilitaryUnit;
import game.attackUnities.AttackUnity;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;

public class Datos {

	private String urlDatos = "jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Oriol
//	private String urlDatos = "jdbc:oracle:thin:@192.168.56.110:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Mar
	//private String urlDatos = "jdbc:oracle:thin:@localhost:1521/xe?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd local

	private String user = "civil";
	private String password = "civil";
	private Connection conn;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Datos() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(this.urlDatos, this.user, this.password);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Datos(int id) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(this.urlDatos, this.user, this.password);
			this.id=id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int crearNuevaPartida(String name) {
		try {
			String insert = "Insert into Civilization_stats(\"name\") values(?)";

			PreparedStatement ps = conn.prepareStatement(insert, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(query);
			rs.next();
			int id = rs.getInt(1);
			return id;
		} catch (Exception e) {
			return 0;
		}

	}

	public ArrayList<String[]> mostrarPartidasNombre(String name) {
		ArrayList<String[]> resultados = new ArrayList<>();

		try {
			String search = "select id_civilization,\"name\",battles_counter from civilization_stats where lower(\"name\") like lower('%"
					+ name + "%')order by \"name\"";
			Statement st = conn.createStatement();
			String[] fila = new String[3];
			ResultSet rs = st.executeQuery(search);
			while (rs.next()) {
				fila[0] = String.valueOf(rs.getInt(1));
				fila[1] = rs.getString(2);
				fila[2] = String.valueOf(rs.getInt(3));
				resultados.add(fila);
				System.out.println("fila numero" + resultados.size() + ": {id: " + fila[0] + ", nombre: " + fila[1]
						+ ", batallas realizadas: " + fila[2] + "}");
			}

			return resultados;
		} catch (Exception e) {
			System.out.println("error!!!!");
			return resultados;
		}

	}

	public ArrayList<String> cargarPartida(int id) {
		ArrayList<String> partida = new ArrayList<String>();
		try {
			String search = "select * from civilization_stats where id_civilization = " + id;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(search);
			this.id=id;
			rs.next();
			for (int i = 1; i < 15; i++) {
				if (partida.size() != 1) {
					partida.add(String.valueOf(rs.getInt(i)));
					System.out.println(rs.getInt(i));

				} else {
					partida.add(rs.getString(i));
					System.out.println(rs.getString(i));

				}
			}

			return partida;
		} catch (Exception e) {
			System.out.println("*******\nError!\nEl ID " + id + " no existe!\n*******");
			return partida;

		}

	}

	public void borrarPartida(int id) {
		try {
			String delete = "delete from civilization_stats where id_civilization = ?";
			PreparedStatement ps = conn.prepareStatement(delete);

			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Partida eliminada");

		} catch (Exception e) {
			System.out.println("*******\nError!\nEl ID " + id + " no existe!\n*******");
			e.printStackTrace();
		}

	}
	
	public void crearSoldado(MilitaryUnit mUnit) {
		if (mUnit instanceof AttackUnity) {
			if (mUnit instanceof Swordsman) {
				String insert= "insert into attack_units_stats(id_civilization,type,armor,base_damage) values(?,?,?,?)";
				try {
					PreparedStatement ps = conn.prepareStatement(insert);
					ps.setInt(1, getId());
					ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
					ps.setInt(3, mUnit.getActualArmor());
					ps.setInt(4, ((AttackUnity) mUnit).getBaseDamage());
					ps.executeUpdate();
					System.out.println("Introducido");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}

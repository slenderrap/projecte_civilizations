package bbdd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import game.Battle;
import game.MilitaryUnit;
import game.attackUnities.AttackUnity;
import game.defenseUnities.DefenseUnit;
import game.specialUnities.Magician;
import game.specialUnities.Priest;
import game.specialUnities.SpecialUnit;

public class Datos {

//	private String urlDatos = "jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Oriol
	private String urlDatos = "jdbc:oracle:thin:@192.168.56.110:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Mar
//	private String urlDatos = "jdbc:oracle:thin:@localhost:1521/xe?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd local

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
		}
	}

	public Datos(int id) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(this.urlDatos, this.user, this.password);
			this.id = id;
		} catch (Exception e) {
		}
	}

	public int crearNuevaPartida(String name) {
		try {
			String insert = "Insert into Civilization_stats(\"name\") values(?)";

			PreparedStatement ps = conn.prepareStatement(insert, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, name);
			ps.executeUpdate();
			ps.close();
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
			rs.close();
			st.close();
			return id;
		} catch (Exception e) {
			return 0;
		}

	}

	public Object[][] mostrarPartidasNombre(String name) {
		ArrayList<String[]> resultados = new ArrayList<>();

		try {
			String search = "select id_civilization,\"name\",battles_counter from civilization_stats where lower(\"name\") like lower('%"
					+ name + "%')order by \"name\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(search);
			while (rs.next()) {
				String[] fila = new String[3];
				fila[0] = String.valueOf(rs.getInt(1));
				fila[1] = rs.getString(2);
				fila[2] = String.valueOf(rs.getInt(3));
				resultados.add(fila);
			}

			rs.close();
			st.close();


			Object[][] data = new String[resultados.size()][3];
			for (int i=0; i<resultados.size(); i++) {
				data[i][0] = resultados.get(i)[0];
				data[i][1] = resultados.get(i)[1];
				data[i][2] = resultados.get(i)[2];
			}
			return data;
		} catch (Exception e) {
			System.out.println("error!!!!");
			return null;

		}

	}

	public ArrayList<String> cargarPartida(int id) {
		ArrayList<String> partida = new ArrayList<String>();
		try {
			String search = "select * from civilization_stats where id_civilization = " + id;
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(search);
			this.id = id;
			rs.next();
			for (int i = 1; i < 15; i++) {
				if (partida.size() != 1) {
					partida.add(String.valueOf(rs.getInt(i)));

				} else {
					partida.add(rs.getString(i));

				}
			}
			rs.close();
			st.close();

			return partida;
		} catch (Exception e) {
			return partida;

		}

	}

	public void borrarPartida(int id) {
		try {
			String delete = "delete from civilization_stats where id_civilization = ?";
			PreparedStatement ps = conn.prepareStatement(delete);

			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
		}

	}

	public void crearSoldado(MilitaryUnit mUnit) {
		if (mUnit instanceof AttackUnity) {

			String insert = "insert into attack_units_stats(id_civilization,type,armor,base_damage) values(?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1, getId());
				ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
				ps.setInt(3, mUnit.getActualArmor());
				ps.setInt(4, ((AttackUnity) mUnit).getBaseDamage());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (mUnit instanceof DefenseUnit) {
			String insert = "insert into defense_units_stats(id_civilization,type,armor,base_damage) values(?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1, getId());
				ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
				ps.setInt(3, mUnit.getActualArmor());
				ps.setInt(4, ((DefenseUnit) mUnit).getBaseDamage());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (mUnit instanceof SpecialUnit) {
			if (mUnit instanceof Magician) {
				String insert = "insert into special_units_stats(id_civilization,type,base_damage) values(?,?,?)";
				try {
					PreparedStatement ps = conn.prepareStatement(insert);
					ps.setInt(1, getId());
					ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
					ps.setInt(3, ((SpecialUnit) mUnit).getBaseDamage());
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (mUnit instanceof Priest) {
				String insert = "insert into special_units_stats(id_civilization,type) values(?,?)";
				try {
					PreparedStatement ps = conn.prepareStatement(insert);
					ps.setInt(1, getId());
					ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void crearConstruccion(int i) {

		if (i == 1) {
			String update = "update civilization_stats set farm_counter=farm_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 2) {
			String update = "update civilization_stats set smithy_counter=smithy_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
			}
		} else if (i == 3) {
			String update = "update civilization_stats set carpentry_counter=carpentry_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
			} catch (SQLException e) {
			}
		} else if (i == 4) {
			String update = "update civilization_stats set magicTower_counter=magicTower_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
			}
		} else if (i == 5) {
			String update = "update civilization_stats set church_counter=church_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
			}
		}
	}

	public void crearIncrementoTecnologia(int i) {
		if (i == 1) {
			String update = "update civilization_stats set technology_attack_level =technology_attack_level +1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
			}
		} else if (i == 2) {
			String update = "update civilization_stats set technology_defense_level = technology_defense_level+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
			}
		}
	}

	public void actualizarRecursos(int food,int wood,int iron,int mana) {
		String update = "update civilization_stats set food_amount = "+food+", wood_amount = "+wood+ ", iron_amount = " + iron + ", mana_amount = " + mana + " where id_civilization = "+getId();
		try {
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.executeUpdate(update);
			st.close();
		} catch (SQLException e) {
		}
		
	}
	
	public ArrayList<String[]> recuperarSoldadosAtaque(){
		ArrayList<String[]> soldados = new ArrayList<String[]>();
		String query = "Select type, armor, base_damage from attack_units_stats where id_civilization = "+getId();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String[] fila= new String[3];
				fila[0] = rs.getString(1);
				fila[1] = String.valueOf(rs.getInt(2));
				fila[2] = String.valueOf(rs.getInt(3));
				soldados.add(fila);
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
		}
		
		return soldados; 
		
	}
	

	public ArrayList<String[]> recuperarSoldadosDefensa(){
		ArrayList<String[]> soldados = new ArrayList<String[]>();
		String query = "Select type, armor, base_damage from defense_units_stats where id_civilization = "+getId();
		String[] fila= new String[3];
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				fila[0] = rs.getString(1);
				fila[1] = String.valueOf(rs.getInt(2));
				fila[2] = String.valueOf(rs.getInt(3));
				soldados.add(fila);
			}
			rs.close();
			st.close();
			
			
		} catch (SQLException e) {
		}
		return soldados; 
		
	}
	
	public ArrayList<String[]> recuperarSoldadosSpecial(){
		ArrayList<String[]> soldados = new ArrayList<String[]>();
		String query = "Select type, armor, base_damage from special_units_stats where id_civilization = "+getId();
		String[] fila= new String[3];
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				fila[0] = rs.getString(1);
				fila[1] = String.valueOf(rs.getInt(2));
				fila[2] = String.valueOf(rs.getInt(3));
				soldados.add(fila);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
		}
		return soldados; 
		
	}
	
	
	public void guardarPartida(int food,int wood,int iron, int mana, int battles) {
		String update = "update civilization_stats set food_amount = "+food+", wood_amount = "+wood+ 
				", iron_amount = " + iron + ", mana_amount = " + mana+" battles_counter = " +battles+ " where id_civilization = (?)";
		try {
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setInt(1, getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
		}
		
	}
	
	public void insertLogs(String log) {
//		String insert= "Insert into Battle_log(id_civilization,log_entry) VALUES (?,?)";
//		PreparedStatement ps;
//		try {
//			ps = conn.prepareStatement(insert);
//			ps.setInt(1, getId());
//			ps.setString(2, log);
//			ps.executeUpdate();
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

}

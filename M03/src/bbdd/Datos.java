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
import game.defenseUnities.DefenseUnit;
import game.specialUnities.Magician;
import game.specialUnities.Priest;
import game.specialUnities.SpecialUnit;

public class Datos {

	private String urlDatos = "jdbc:oracle:thin:@192.168.56.2:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Oriol
//	private String urlDatos = "jdbc:oracle:thin:@192.168.56.110:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"; // bbdd maquina virtual Mar
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
			System.out.println(e.getMessage());
		}
	}

	public Datos(int id) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(this.urlDatos, this.user, this.password);
			this.id = id;
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
			this.id = id;
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

			String insert = "insert into attack_units_stats(id_civilization,type,armor,base_damage) values(?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1, getId());
				ps.setString(2, String.valueOf(mUnit.getClass().getSimpleName()));
				ps.setInt(3, mUnit.getActualArmor());
				ps.setInt(4, ((AttackUnity) mUnit).getBaseDamage());
				ps.executeUpdate();
				System.out.println("Introducido");
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
				System.out.println("Introducido");
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
					System.out.println("Introducido");
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
					System.out.println("Introducido");
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
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 2) {
			String update = "update civilization_stats set smithy_counter=smithy_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 3) {
			String update = "update civilization_stats set carpentry_counter=carpentry_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 4) {
			String update = "update civilization_stats set magicTower_counter=magicTower_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 5) {
			String update = "update civilization_stats set church_counter=church_counter+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
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
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (i == 2) {
			String update = "update civilization_stats set technology_defense_level = technology_defense_level+1 where id_civilization = (?)";
			try {
				PreparedStatement ps = conn.prepareStatement(update);
				ps.setInt(1, getId());
				ps.executeUpdate();
				System.out.println("Edificio creado");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarRecursos(int food,int wood,int iron,int mana) {
		String update = "update civilization_stats set food_amount = "+food+", wood_amount = "+wood+ ", iron_amount = " + iron + ", mana_amount = " + mana + " where id_civilization = "+getId();
		try {
			Statement st = conn.createStatement();
			
			st.executeUpdate(update);
			System.out.println("Datos guardados");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


}

package test1827;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class test1 {

	public static void main(String[] args) throws SQLException{
		Connection con = null;
		Statement stm = null;
		ResultSet Rs = null;
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb1";
		String dbUserName = "sunkevu4";
		String dbUserPassword = "0951652170s";
		try {
			//
			Class.forName("org.postgresql.Driver");
			System.out.println("connected");
		} catch (ClassNotFoundException e) {
			   System.out.println("Could not find the JDBC driver!");
	           System.exit(1);
		}
		try {
			// подключение к базе 
			Connection conn = DriverManager.getConnection(dbUrl, dbUserName,dbUserPassword);
	
			PreparedStatement stmt = conn.prepareStatement("select * from autoincrement2");
			Rs = stmt.executeQuery();
			
			while (Rs.next()) {
		
				System.out.println(Rs.getInt(1)+ " " + Rs.getString(2) + " " +  Rs.getString(3));
			}
			Rs.close();
			stmt.close();
		}catch(Exception ex) { //нужной таблицы в базе не обнаружено 
			//System.out.println(ex.getMessage());
			System.out.println("Запрос в базу не верный/не выполненый.");
			con = DriverManager.getConnection(dbUrl, dbUserName,dbUserPassword);
			stm = con.createStatement();
			stm.executeUpdate("create table autoincrement2 (id serial PRIMARY KEY, username character varying(255), password int)");
			System.out.println("Таблица создана!");
			
			try {
				stm.executeUpdate("insert into autoincrement2 (username,password) values ('roman','1236')");
				stm.executeUpdate("insert into autoincrement2 (username,password) values ('ivan','345')");
				PreparedStatement state = con.prepareStatement("select * from autoincrement2");
				Rs = state.executeQuery();
				while(Rs.next()) {
					System.out.println(" В таблице создана запись с индексом : " + Rs.getInt(1));
				}
				Rs.close();
				stm.close();
			}catch(Exception exep) { //нужной таблицы в базе не обнаружено 
				System.out.println("Не удалось выполнить запрос!");
			}
		}
	}
}

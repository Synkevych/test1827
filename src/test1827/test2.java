package test1827;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class test2 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement stm = null;
		ResultSet Rs = null;
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb1";
		String dbUserName = "sunkevu4";
		String dbUserPassword = "0951652170s";
		//DatabaseMetaData metaData = null;
	
		try {	
		Class.forName("org.postgresql.Driver");
			// здесь осуществляется соединение
			// con = DriverManager.getConnection(dbUrl, dbUserName, dbUserPassword);
			// metaData = con.getMetaData();
			// Rs = metaData.getColumns( null, "schemaName", "tableName", "%");
			// System.out.println(metaData.getUserName());
			//	Rs = metaData.getUserName();
			//  Rs  = metaData.getTypeInfo();
			
			
	try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbUserPassword);
			        Statement statement = connection.createStatement()) {
		
			    statement.execute("create table users(id serial primary key, name varchar(100));");
			    
			    statement.execute("insert into users(name) values('borya'),('petya')");
			    ResultSet rs = statement.executeQuery("select * from users");
			    while (rs.next()) {
			        System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
			    }
			} finally { 
				if (Rs != null) {Rs.close();};
			}
		} catch (Exception e) { System.out.println("Erorr!"); }
		
		}
	}
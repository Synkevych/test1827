package test1827;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class TableData {
	    TableData(Connection conect, String tableName) throws SQLException{
		System.out.println("В таблице "+ tableName + " есть такие данные:"); 
		Statement st = conect.createStatement();
		ResultSet rst = st.executeQuery("select * from " + tableName);
			while (rst.next()) {
				System.out.println(rst.getInt("id") + " : " + rst.getString("name"));
			}
		}
	TableData(Statement statement, String tableName) throws SQLException{
		statement.execute("insert into "+ tableName + "(name) values('borya'),('petya'),('jaruk')");
	}

}
public class test_conection_to_tabel {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb1";
		String dbUserName = "sunkevu4";
		String dbUserPassword = "0951652170s";
		Class.forName("org.postgresql.Driver");
		String tableName = "users6";
// проверка таблицы
		    try (Connection  con = DriverManager.getConnection(dbUrl, dbUserName,dbUserPassword);
		    		ResultSet rs = con.getMetaData().getTables(null, null, tableName, null)) {
		        if  (rs.next()) { 
		            String tName = rs.getString("TABLE_NAME");
		            if (tName != null && tName.equals(tableName)) {
		                System.out.println("Таблица "+ tableName + " найдена в базе данных.");

// вывод данных с таблицы 
		                 TableData gettab = new TableData(con, tableName);
		            } 
		        } 
		        else  { System.out.println("Таблица "+ tableName + " не найдена в базе данных.");
// создание таблицы 
		          try (Statement statement = con.createStatement();) {
		        	   System.out.println("Создание таблицы "+ tableName + "."); 
		        	  	 statement.execute("create table "+ tableName + "(id serial primary key, name varchar(100));");
				    // statement.execute("insert into "+ tableName + "(name) values('borya'),('petya')");
		        	  	 TableData SetData = new TableData(statement, tableName);
// вывод данных с таблицы 
				     TableData gettab = new TableData(con, tableName);
		        		} 
		        } 
		    } catch(Exception e) { System.out.println("Не удалось выполнить запрос!");}

	}
}
package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
	public Connection databaseLink;

	public Connection getConnection() {
		String databaseName = "Tema3";
		String databaseUser = "root";
		String databasePassword = "Minecraft20";
		String url = "jdbc:mysql://localhost/" + databaseName;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		} catch (Exception e) {
			System.out.println("problema in DBC");
		}
		return databaseLink;

	}
}

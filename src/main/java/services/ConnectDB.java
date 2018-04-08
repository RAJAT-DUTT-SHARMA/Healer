/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

import properties.Properties;

/**
 *
 * @author Er.RajatDS
 */

public class ConnectDB {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/healer";
	

	// Database credentials
	static final String USER = Properties.user;
	static final String PASS = Properties.password;

	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Connection");
		}
		return con;

	}
}

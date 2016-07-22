package com.connector.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceCustomers.
 */
public class ServiceCustomers implements IConnector {

	/** The Constant JDBC_DRIVER. */
	static final String JDBC_DRIVER = Messages.getString("ServiceCustomers.0"); //$NON-NLS-1$

	/** The Constant DB_URL. */
	static final String DB_URL = Messages.getString("ServiceCustomers.1"); //$NON-NLS-1$

	/** The Constant USER. */
	// Database credentials
	static final String USER = Messages.getString("ServiceCustomers.2"); //$NON-NLS-1$

	/** The Constant PASS. */
	static final String PASS = Messages.getString("ServiceCustomers.3"); //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.main.IConnector#getConnection()
	 */
	public Connection getConnection() throws SQLException, Exception{
		Connection conn = null;
		// Statement stmt = null;
		try {

			// STEP 2: Register JDBC driver
			Class.forName(Messages.getString("ServiceCustomers.4")); //$NON-NLS-1$

			// STEP 3: Open a connection
			System.out.println("Connecting to database..."); //$NON-NLS-1$
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			System.out.println("Error en parametros de conexion: "+se.getStackTrace()); 
			se.getStackTrace();
		} catch (Exception ex) {
			System.out.println("Error al conectar con la base de datos"); 
			ex.getStackTrace();
		}
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.main.IConnector#getCustomers()
	 */
	@Override
	public ArrayList<Customer> getCustomers() throws SQLException, Exception {
		Connection conn = getConnection();
		Statement stmt = null;
		ArrayList<Customer> customerList = new ArrayList<>();
		Customer customerObject;
		try {

			System.out.println("Creating statement..."); //$NON-NLS-1$
			stmt = conn.createStatement();
			String sql;
			sql = Messages.getString("ServiceCustomers.7"); //$NON-NLS-1$
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {

				customerObject = new Customer();
				customerObject.setId(rs.getInt(Messages.getString("ServiceCustomers.8"))); //$NON-NLS-1$
				customerObject.setFirstName(rs.getString(Messages.getString("ServiceCustomers.9"))); //$NON-NLS-1$
				customerObject.setLastName(rs.getString(Messages.getString("ServiceCustomers.10"))); //$NON-NLS-1$
				customerObject.setEmail(rs.getString(Messages.getString("ServiceCustomers.11"))); //$NON-NLS-1$
				customerList.add(customerObject);
			}

			// STEP 6: Clean-up environment
			// rs.close();
			// stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			customerObject = null;
			closeConnection(conn, stmt);
			System.gc();
		} // end try
		return customerList;
	}

	private void closeConnection(Connection conn, Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end finally try
	}

	public int setNewCustomer(Customer customer) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "insert into visitantes (firstname, lastname, email) values('" + customer.getFirstName()
					+ "','" + customer.getLastName() + "','" + customer.getEmail() + "') ";
			return stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt);
		}
		return 0;
	}

}

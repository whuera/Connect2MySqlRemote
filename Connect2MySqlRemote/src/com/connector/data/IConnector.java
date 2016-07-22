package com.connector.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConnector.
 */
public interface IConnector {
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() throws SQLException, Exception;
	
	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 * @throws SQLException the SQL exception
	 */
	 public ArrayList<Customer> getCustomers() throws SQLException, Exception;
	
	 /**
 	 * Sets the new customer.
 	 *
 	 * @param customer the customer
 	 * @return the int
 	 * @throws SQLException the SQL exception
 	 * @throws Exception the exception
 	 */
 	public int setNewCustomer(Customer customer) throws SQLException, Exception;

}

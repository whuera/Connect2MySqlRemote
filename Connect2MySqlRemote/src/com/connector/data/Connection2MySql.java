package com.connector.data;

import java.sql.*;
import java.util.ArrayList;

public class Connection2MySql {

	public static void main(String[] args) {

		// ServiceCustomers serviceCustomer = new ServiceCustomers();
		IConnector serviceConnector = new ServiceCustomers();
		ArrayList<Customer> listCustomer = new ArrayList<>();
		try {
/*
			Customer customer = new Customer();
			customer.setFirstName("Juan Pedro");
			customer.setLastName("Luna");
			customer.setEmail("jpluna@hotmail.com");
			serviceConnector.setNewCustomer(customer);
			*/
			//listCustomer = serviceConnector.getCustomers();
			//System.out.println(listCustomer.toString());
			if (serviceConnector.getConnection()==null)
			{
				System.out.println("Error de conexion nivel 1");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		System.out.println("Goodbye!");
	}// end main

}

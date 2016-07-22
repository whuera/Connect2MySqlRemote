package com.connector.data;
import java.sql.*;
public class ConnectionMySqlRemote {
	
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://198.27.83.229:3306/mobilpym_bdd";

	   //  Database credentials
	   static final String USER = "mobilpym_admin";
	   static final String PASS = "admin2016";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	     stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT id, firstname, lastname, email FROM visitantes";
	      ResultSet rs = stmt.executeQuery(sql);

	    //  STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String firstname = rs.getString("firstname");
	         String lastname = rs.getString("lastname");
	         String email = rs.getString("email");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Name: " + firstname);
	         System.out.print(", Lastname: " + lastname);
	         System.out.println(", email: " + email);
	      }
	      //STEP 6: Clean-up environment
	    //  rs.close();
	    //  stmt.close();
	    //  conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	

	
}

package com.zoo.dragons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zoo.dragons.dao.DatabaseConstants;
import com.zoo.dragons.dao.ZooDB;
import com.zoo.dragons.data.Dragon;
import com.zoo.dragons.data.Location;

public class Main {
	
	public static void main(String [] args)
	{

//		MySQLAccess dao = new MySQLAccess();
//        try {
////			dao.readDataBase();
//        	int size = dao.getAllComments().length;
//        	dao.deleteCommentByUser("Batool");
//        	int after = dao.getAllComments().length;
//        	Comment comment = new Comment();
//        	
//        	dao.insertComment(comment);
//        	System.out.println();
//        	
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		testConnection();
	}
	
	private static void testConnection()
	{
		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(DatabaseConstants.DB_URL,
				DatabaseConstants.DB_USERNAME, DatabaseConstants.DB_PASSWORD)) {
		    System.out.println("Database connected!");
		    ZooDB database = new ZooDB();
		   
		    Location[] locations = database.getAllLocations();
		    System.out.println("Location Table:");
		    for(Location l : locations)
		    	System.out.println(l);
		    
		    Dragon[] dragons = database.getAllDragons();
		    System.out.println("Dragon Table:");
		    for(Dragon d:dragons)
		    	System.out.println(d);
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

}

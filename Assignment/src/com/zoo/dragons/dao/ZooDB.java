package com.zoo.dragons.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zoo.dragons.data.Dragon;
import com.zoo.dragons.data.Location;

public class ZooDB {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void openConnection() throws Exception {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Setup the connection with the DB
		connection = DriverManager.getConnection(DatabaseConstants.DB_URL, DatabaseConstants.DB_USERNAME,
				DatabaseConstants.DB_PASSWORD);
	}

	public Location[] getAllLocations() {

		try {
			openConnection();
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from zoo.location");
			return getAllLocations(resultSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Dragon[] getAllDragons() {

		try {
			openConnection();
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from zoo.dragon, zoo.location where dragon.locationNo = location.locationid");
			return getDragons(resultSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void createLocation(String locationName)
	{
		try {
			openConnection();
			// PreparedStatements can use variables and are more efficient
			preparedStatement = connection
					.prepareStatement("insert into  zoo.location values (default, ?)");
			preparedStatement.setString(1, locationName);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// failed to open connection
			e.printStackTrace();
		} finally {
			close();
		}

	}
	
	public void updateDragonLocation(int dragonId, int locationId)
	{
		try {
			openConnection();
			// PreparedStatements can use variables and are more efficient
			preparedStatement = connection
					.prepareStatement("update zoo.dragon set locationNo=? where dragonid=?");
			preparedStatement.setInt(1, locationId);
			preparedStatement.setInt(2, dragonId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// failed to open connection
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	private Location[] getAllLocations(ResultSet resultSet2) throws SQLException {
		ArrayList<Location> locations = new ArrayList<Location>();
		while (resultSet.next()) {
			Location location = new Location();
			location.setId(resultSet.getInt("locationid"));
			location.setName(resultSet.getString("locationName"));

			locations.add(location);
		}

		return locations.toArray(new Location[0]);
	}

	private Dragon[] getDragons(ResultSet resultSet) throws SQLException {
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		while (resultSet.next()) {
			Dragon dragon = new Dragon();
			dragon.setId(resultSet.getInt("dragonid"));
			dragon.setName(resultSet.getString("dragonName"));
			dragon.setColor(resultSet.getString("color"));
			dragon.setFavorateFood(resultSet.getString("Favoratefood"));
			dragon.setLocationId(resultSet.getInt("locationNo"));
			dragon.setLocationName(resultSet.getString("locationName"));
			dragons.add(dragon);
		}

		return dragons.toArray(new Dragon[0]);
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
			System.out.println("\n\n***** closing conniction *****\n");
		} catch (Exception e) {

		}
	}

}

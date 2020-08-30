package com.zoo.dragons.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zoo.dragons.dao.ZooDB;
import com.zoo.dragons.data.Dragon;
import com.zoo.dragons.data.Location;

@Path("/")
public class ZooRESTService {

	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "Zoo Service Successfully started..";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/locations")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLocations(InputStream incomingData) {
		ZooDB database = new ZooDB();

		Location[] locations = database.getAllLocations();
		String result = new Gson().toJson(locations);

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/dragons")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDragons(InputStream incomingData) {
		ZooDB database = new ZooDB();

		Dragon[] dragons = database.getAllDragons();
		String result = new Gson().toJson(dragons);

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/location")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLocation(InputStream incomingData) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}

		String json = sb.toString();
		System.out.println("Data Received: " + json);

		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(json);

		ZooDB database = new ZooDB();
		database.createLocation(object.get("name").getAsString());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(sb.toString()).build();
	}

	@POST
	@Path("/dragons/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDragonLocation(InputStream incomingData, @PathParam("id") int dragonId) {
		System.out.println(dragonId);
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}

		String json = sb.toString();
		System.out.println("Data Received: " + json);

		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(json);

		ZooDB database = new ZooDB();
		database.updateDragonLocation(dragonId, object.get("locationid").getAsInt());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(sb.toString()).build();
	}

}

package com.myapp;

import java.util.Map; 
import java.util.HashMap;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
 
@Path("/user")
public class UserRS {

	private static Map<Long, User> users = new HashMap<Long, User>();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAllUsers() {
		return Response.ok(new Gson().toJson(users)).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getUser(@PathParam("id") Long id) {
		if (users.containsKey(id)) {
			return Response.ok(new Gson().toJson(users.get(id))).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response updateUser(@PathParam("id") Long id, @FormParam("user") String jsonUser) {
		if (!users.containsKey(id)) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		User user = new Gson().fromJson(jsonUser, User.class);
		user.setId(id);

		users.put(id, user);

		return Response.ok(new Gson().toJson(users.get(id))).build();
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response createUser(@FormParam("user") String jsonUser) {
		User user = new Gson().fromJson(jsonUser, User.class);
		user.setId(users.keySet().size() + 1l);

		users.put(user.getId(), user);

		return Response.ok(new Gson().toJson(user)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response deleteUser(@PathParam("id") Long id) {
		if (!users.containsKey(id)) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		users.remove(id);

		return Response.ok().build();
	}
}

package com.myapp;
 
import javax.ws.rs.*;
 
@Path("/user")
public class UserRS {

	@GET
	@Path("/")
	public String helloworld() {
		return "[]";
	}
}

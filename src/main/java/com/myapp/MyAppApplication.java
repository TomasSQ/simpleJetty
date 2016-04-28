package com.myapp;
 
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
 
 
public class MyAppApplication extends Application {

	private Set<Object> singletons = new HashSet();
	private Set<Class<?>> empty = new HashSet();
 
	public MyAppApplication() {
		this.singletons.add(new UserRS());
	}
 
	public Set<Class<?>> getClasses() {
		return this.empty;
	}
 
	public Set<Object> getSingletons() {
		return this.singletons;
	}

}

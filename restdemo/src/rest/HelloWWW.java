package rest;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("test")
public class HelloWWW {
	
	@GET
	public void testMethod() {
		System.out.println("test method called...");
	}
	
	@GET
	@Path("test2")
	public void testMethod2() {
		System.out.println("test method 2 called...");
	}
	
	@GET
	@Path("hello")
	public Response sayHelloResponse() {
		return Response.status(200).entity("<h1>Welcome To REST</h1>").build();
	}
	
	@GET
	@Path("hello2/{uname}")
	public Response sayHelloResponse2(@PathParam("uname")String name) {
		return Response.status(200).entity("<h1>Welcome To REST</h1>"+name).build();
	}
	
	@GET
	@Path("hello3/{uname}/{myname}")
	public Response sayHelloResponse3(@PathParam("uname")String name,@PathParam("myname") String myname) {
		return Response.status(200).entity("<h1>Welcome To REST</h1>"+name+":"+myname).build();
	}
	
	@GET
	@Path("hello4")
	public Response sayHelloResponse4(@QueryParam("uname") String name,@QueryParam("myname") String myname) {
		return Response.status(200).entity("<h1>Welcome To REST</h1>"+name+":"+myname).build();
	}
	
	@GET
	@Path("hello5")
	public Response sayHelloResponse5(@Context UriInfo uriInfo) {
		String name=uriInfo.getQueryParameters().getFirst("uname");
		String myname=uriInfo.getQueryParameters().getFirst("myname");
		return Response.status(200).entity("<h1>Welcome To REST</h1>"+name+":"+myname).build();
	}
	
	@GET
	@Path("hello6")
	public Response sayHelloResponse6(@MatrixParam("jsessionid") String id) {		
		return Response.status(200).entity("<h1>The session id is...:"+id+"</h1>").build();
	}
	
	@POST
	@Path("hello7")
	public Response sayHelloResponse7(@FormParam("uname") String name,@FormParam("myname") String myname) {		
		return Response.status(200).entity("<h1>Welcome To REST</h1>"+name+":"+myname).build();
	}
}



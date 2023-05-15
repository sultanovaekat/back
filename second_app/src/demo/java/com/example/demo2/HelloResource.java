package com.example.demo2;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@Path("/hello-world")
public class HelloResource {
    @Inject
@Default
private Interconnectable interconnector;
    @GET
    @Produces("text/plain")
    public String hello() {
        interconnector.info();
        return "Hello, World!";
    }

   
}
package com.example.demo2;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/notification")
public class Resource {
    @Inject @Default
private Interconnectable interconnector;
    @GET
    @Produces("text/plain")
    public String notifyClients() {
        interconnector.info();
        return "Client notified";
    }
    @GET
    @Produces("text/plain")
    @Path("/check")
    public String test() {
        return "all ok";
    }
}
package com.natnan.dropwizard.executor.resource;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;

@Path("/jersey")
public class ExecutorResource {

  private final Client client;

  @Inject
  public ExecutorResource(Client client) {
    this.client = client;
  }

  @GET
  public String get() throws ExecutionException, InterruptedException {
    return client.target("http://www.google.com").request().async().get().get().readEntity(String.class);
  }
}

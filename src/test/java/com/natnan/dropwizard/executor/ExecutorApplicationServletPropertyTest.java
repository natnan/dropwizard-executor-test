package com.natnan.dropwizard.executor;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit.DropwizardAppRule;

import static org.junit.Assert.assertEquals;

public class ExecutorApplicationServletPropertyTest {

  @Rule
  public DropwizardAppRule<ExecutorConfiguration> applicationRule = new DropwizardAppRule<ExecutorConfiguration>(ExecutorApplicationServletProperty.class, null);

  @Test
  public void test(){
    Response response = JerseyClientBuilder.createClient().target("http://localhost:8080/jersey").request().get();

    assertEquals(response.getStatus(), 200);
    System.out.println(response.readEntity(String.class));
  }

  @Test
  public void test2(){
    Response response = JerseyClientBuilder.createClient().target("http://localhost:8080/jersey").request().get();

    assertEquals(response.getStatus(), 200);
    System.out.println(response.readEntity(String.class));
  }
}

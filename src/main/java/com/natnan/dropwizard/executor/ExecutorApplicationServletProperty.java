package com.natnan.dropwizard.executor;

import com.natnan.dropwizard.executor.resource.ExecutorResource;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.servlet.ServletProperties;

import javax.ws.rs.client.Client;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

public class ExecutorApplicationServletProperty extends Application<ExecutorConfiguration> {

  @Override
  public void run(ExecutorConfiguration configuration, Environment environment) throws Exception {
    final Client jersey = new JerseyClientBuilder(environment).using(configuration.getJersey()).build("jersey");

    ServiceLocator locator = ServiceLocatorUtilities.bind(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(jersey).to(Client.class);
      }
    });

    environment.getApplicationContext().getAttributes().setAttribute(ServletProperties.SERVICE_LOCATOR, locator);

    environment.jersey().register(ExecutorResource.class);
  }
}

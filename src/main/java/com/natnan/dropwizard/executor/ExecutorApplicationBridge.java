package com.natnan.dropwizard.executor;

import com.natnan.dropwizard.executor.resource.ExecutorResource;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.extras.ExtrasUtilities;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

public class ExecutorApplicationBridge extends Application<ExecutorConfiguration> {

  @Override
  public void run(ExecutorConfiguration configuration, Environment environment) throws Exception {
    final Client jersey = new JerseyClientBuilder(environment).using(configuration.getJersey()).build("jersey");

    ServiceLocator locator = ServiceLocatorUtilities.bind(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(jersey).to(Client.class);
      }
    });

    InjectionBridge.setApplicationServiceLocator(locator);
    environment.jersey().register(InjectionBridge.class);

    environment.jersey().register(ExecutorResource.class);
  }
  public static class InjectionBridge implements Feature
  {
    private static ServiceLocator _applicationServiceLocator;

    private final ServiceLocator _serviceLocator;

    @Inject
    private InjectionBridge(ServiceLocator serviceLocator)
    {
      _serviceLocator = serviceLocator;
    }

    @Override
    public boolean configure(FeatureContext context)
    {
      if (_applicationServiceLocator != null)
        ExtrasUtilities.bridgeServiceLocator(_serviceLocator, _applicationServiceLocator);
      return true;
    }

    public static void setApplicationServiceLocator(ServiceLocator applicationServiceLocator)
    {
      _applicationServiceLocator = applicationServiceLocator;
    }
  }
}

package com.natnan.dropwizard.executor;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

public class ExecutorConfiguration extends Configuration {

  private JerseyClientConfiguration jersey = new JerseyClientConfiguration();

  public JerseyClientConfiguration getJersey() {
    return jersey;
  }

  public void setJersey(JerseyClientConfiguration jersey) {
    this.jersey = jersey;
  }
}

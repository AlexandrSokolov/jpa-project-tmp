package com.a.b.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.a.b.rest.jackson.JacksonProvider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerSideJacksonProvider implements ContextResolver<ObjectMapper> {

  private ObjectMapper objectMapper;

  public ServerSideJacksonProvider(){
    this.objectMapper = JacksonProvider.instance().objectMapper();
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return objectMapper;
  }

}

package com.a.b.rest.config;

import com.google.common.collect.Sets;
import com.a.b.rest.exception.mappers.WebApplicationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath(JAXRSConfiguration.APPLICATION_PATH)
public class JAXRSConfiguration extends Application {

  //see src/main/webapp/WEB-INF/jboss-web.xml
  public static final String CONTEXT_ROOT = "/project";
  public static final String APPLICATION_PATH = "/rest";

  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      ServerSideJacksonProvider.class,
      WebApplicationExceptionMapper.class
    );
  }
}

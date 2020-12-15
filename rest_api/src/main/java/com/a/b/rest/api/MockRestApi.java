package com.a.b.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Mock rest service for correct rest documentation generation,
 *  Remove it, as soon as you add any other REST API classes
 */
@Path(MockRestApi.SERVICE_REST_URL)
@Produces("text/plain")
public interface MockRestApi {

  String SERVICE_REST_URL = "/";

  @GET
  Response get();
}

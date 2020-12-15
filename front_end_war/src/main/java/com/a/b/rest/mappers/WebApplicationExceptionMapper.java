package com.a.b.rest.exception.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

  @Override
  public Response toResponse(WebApplicationException e) {
    return Response.status(e.getResponse().getStatus())
      .entity(extractOriginalCause(e))
      .type(MediaType.TEXT_PLAIN)
      .build();
  }

  private String extractOriginalCause(final Throwable e){
    if (e.getCause() != null
        //to avoid stackoverflow:
        && e != e.getCause().getCause()){
      return extractOriginalCause(e.getCause());
    } else {
      return e.getMessage();
    }
  }
}

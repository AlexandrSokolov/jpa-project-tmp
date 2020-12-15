package com.a.b.rest.app;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class JaxRsHandlerUtils {

  public static <I> void handle(
    final I input,
    final Consumer<I> consumer){
    try {
      consumer.accept(input);
    } catch (Exception e) {
      throw new WebApplicationException(
        "Could not handle: " + e.getMessage(),
        e,
        Response.Status.CONFLICT);
    }
  }

  public static <R> R handle(final Supplier<R> supplier){
    try {
      return supplier.get();
    } catch (Exception e) {
      throw new WebApplicationException(
        "Could not handle: " + e.getMessage(),
        e,
        Response.Status.CONFLICT);
    }
  }

  public static <I, V, R> R validateAndHandle(
    final I input,
    final Function<I, V> validator,
    final Function<V, R> handler){
    try {
      V valid = validator.apply(input);
      try {
        return handler.apply(valid);
      } catch (Exception e) {
        throw new WebApplicationException(
          "Could not handle: " + e.getMessage(),
          e,
          Response.Status.CONFLICT);
      }
    } catch (WebApplicationException a) {
      throw a;
    } catch (Exception ve) {
      throw new WebApplicationException(
        "Not valid: " + ve.getMessage(),
        ve,
        Response.Status.BAD_REQUEST);
    }
  }
}

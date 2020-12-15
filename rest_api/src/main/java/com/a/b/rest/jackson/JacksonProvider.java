package com.a.b.rest.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.a.b.rest.jackson.deserializer.LocalDateTimeDeserializer;
import com.a.b.rest.jackson.serializer.LocalDateTimeSerializer;
import com.a.b.rest.jackson.serializer.MoneySerializer;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * You can use it by the jax rs client:
 *  Client client = ClientBuilder.newClient();
 *  client.register(JacksonProvider.instance());
 *
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JacksonProvider extends JacksonJaxbJsonProvider  {

  private ObjectMapper mapper;

  public static JacksonProvider instance(){
    return new JacksonProvider();
  }

  public ObjectMapper objectMapper() {
    return this.mapper;
  }

  private JacksonProvider() {
    super();
    this.mapper = _mapperConfig.getConfiguredMapper() != null
      ? _mapperConfig.getConfiguredMapper() : _mapperConfig.getDefaultMapper();

    configureMapper(mapper);
  }

  private void configureMapper(final ObjectMapper mapper){

    /*this configuration affects:
    * java.util.Date
    * java.sql.Date
    * java.sql.Timestamp
    */
    mapper.setDateFormat(DateTimeUtils.instance().dateFormat());

    //to be able to apply jackson and enunciate annotations on private fields:
    //see also: https://github.com/stoicflame/enunciate/issues/1035
    mapper.setVisibility(
      mapper
        .getSerializationConfig()
        .getDefaultVisibilityChecker()
        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
        .withGetterVisibility(JsonAutoDetect.Visibility.ANY));

    // not default
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
    mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    mapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);

    // defaults
    mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    //json pretty output
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

    SimpleModule module = new SimpleModule();
    configureSerializers(module);
    configureDeserializers(module);
    mapper.registerModule(module);
  }

  private void configureSerializers(final SimpleModule module) {
    module.addSerializer(BigDecimal.class, new MoneySerializer());
    module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
  }

  private void configureDeserializers(final SimpleModule module) {
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
  }
}

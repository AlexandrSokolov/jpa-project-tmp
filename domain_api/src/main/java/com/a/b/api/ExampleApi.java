package com.a.b.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExampleApi {

  long getId();

  void setId(long id);

  String getName();

  void setName(String name);

  BigDecimal getDecimalField();

  void setDecimalField(BigDecimal decimalField);

  LocalDateTime getLocalDateTime();

  void setLocalDateTime(LocalDateTime localDateTime);

}

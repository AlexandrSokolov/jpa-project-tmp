package com.a.b.rest.jackson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public enum DateTimeUtils {

  INSTANCE;

  String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  String DATE_PATTERN = "yyyy-MM-dd";
  String TIME_PATTERN = "HH:mm:ss";

  // do not use `ZonedDateTime.now().getOffset()`, in summer for Berlin it shows +02:00
  // it includes daylight saving time, which might changes during a year
  // to get the standard time zone offset, which is for Berlin: +01:00 use:
  // see https://stackoverflow.com/questions/41427384/how-to-get-default-zoneoffset-in-java8
  ZoneOffset systemOffset = LocalDate.of( 2017 , 12 , 25 )
    .atStartOfDay( ZoneId.systemDefault() )
    .getOffset();


  public static DateTimeUtils instance(){
    return INSTANCE;
  }

  public DateTimeFormatter dateTimeFormatter(){
    return DateTimeFormatter.ISO_OFFSET_DATE_TIME
        .withLocale( Locale.getDefault() )
        .withZone( ZoneId.systemDefault() );
  }

  public DateTimeFormatter dateFormatter(){
    return DateTimeFormatter.ofPattern(DATE_PATTERN)
      .withLocale( Locale.getDefault() )
      .withZone( ZoneId.systemDefault() );

  }

  public DateTimeFormatter timeFormatter(){
    return DateTimeFormatter.ofPattern(TIME_PATTERN)
      .withLocale( Locale.getDefault() )
      .withZone( ZoneId.systemDefault() );

  }

  public DateFormat dateFormat(){
    DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
    dateFormat.setTimeZone(TimeZone.getDefault());
    return dateFormat;
  }

  public ZoneOffset systemOffset() {
    return systemOffset;
  }
}

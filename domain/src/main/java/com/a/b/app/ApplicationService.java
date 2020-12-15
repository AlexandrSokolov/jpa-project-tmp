package com.a.b.app;

import com.google.common.collect.Lists;
import com.a.b.api.ExampleApi;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ApplicationService {

  public List<ExampleApi> items(){
    return Lists.newArrayList();
  }
}

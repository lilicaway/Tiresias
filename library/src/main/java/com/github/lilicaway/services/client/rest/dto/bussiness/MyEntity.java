package com.github.lilicaway.services.client.rest.dto.bussiness;

import java.util.ArrayList;
import java.util.List;

import com.github.lilicaway.services.client.rest.dto.base.BaseResponse;

public class MyEntity extends BaseResponse {

  private final List<Field> fields;
  private final List<String> components;

  public MyEntity() {
    this.fields = new ArrayList<Field>();
    this.components = new ArrayList<String>();
  }

  public List<Field> getFields() {
    return fields;
  }

  public List<String> getComponents() {
    return components;
  }

  public void addAllFields(List<Field> fields) {
    fields.addAll(fields);
  }

  public void addAllComponents(List<String> components) {
    components.addAll(components);
  }

}

package com.github.lilicaway.services.client.rest.dto.bussiness;

public class Field {
  private Object value;
  private String name;
  private String type;

  private String component;

  public Field() {}

  public Field(String name, String type, String block, String component) {
    this.name = name;
    this.type = type;
    this.component = component;
  }

  public Object getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getComponent() {
    return component;
  }

}

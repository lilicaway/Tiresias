package com.github.lilicaway.tiresias.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {
  @JsonProperty("3h")
  double data;

  public double getData() {
    return data;
  }

  public void setData(double data) {
    this.data = data;
  }

}

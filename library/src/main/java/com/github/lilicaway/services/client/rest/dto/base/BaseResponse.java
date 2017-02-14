package com.github.lilicaway.services.client.rest.dto.base;

import java.io.Serializable;

public class BaseResponse implements Serializable {

  private ResponseType type;

  public BaseResponse() {}

  public ResponseType getType() {
    return type;
  }

  public void setType(ResponseType type) {
    this.type = type;
  }
}

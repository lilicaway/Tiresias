package com.github.lilicaway.services.client.rest.httpUtils;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface RequestWithEntity<T> {

  /**
   * 
   * @param requestEntity represents a request object with Http headers coming from a user's
   *        browser.
   * @return the response entity with the response gotten from the server.
   */
  ResponseEntity<T> call(HttpEntity<Void> requestEntity);

}

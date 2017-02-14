package com.github.lilicaway.services.client.rest.httpUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import com.google.common.annotations.VisibleForTesting;

@Component
public class HttpGenericHandler {

  public <T> T callAndHandleHttp(HttpServletRequest request, HttpServletResponse response,
      RequestWithEntity<T> requestWithEntity) throws IOException {

    HttpEntity<Void> requestEntity = createHttpEntityFromRequest(request);

    try {
      ResponseEntity<T> res = requestWithEntity.call(requestEntity);
      HttpHeaders responseHeaders = res.getHeaders();
      addHeadersToResponse(responseHeaders, response);
      return res.getBody();
    } catch (HttpStatusCodeException e) {
      HttpHeaders responseHeaders = e.getResponseHeaders();
      addHeadersToResponse(responseHeaders, response);
      response.sendError(e.getStatusCode().value(), e.getStatusText());
      return null;
    }
  }

  @VisibleForTesting
  protected HttpEntity<Void> createHttpEntityFromRequest(HttpServletRequest request) {
    HttpHeaders headers = new HttpHeaders();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      headers.set(headerName, headerValue);
    }
    headers.remove("host");

    HttpEntity<Void> requestEntity = new HttpEntity<Void>(headers);
    return requestEntity;
  }

  @VisibleForTesting
  protected void addHeadersToResponse(HttpHeaders responseHeaders, HttpServletResponse response) {
    for (Entry<String, List<String>> entry : responseHeaders.entrySet()) {
      String headerName = entry.getKey();
      if (isForbiddenHeader(headerName)) {
        continue;
      }
      for (String headerValue : entry.getValue()) {
        response.addHeader(headerName, headerValue);
      }
    }
  }

  /**
   * This returns false for headers that we should not be blindly returning from the service, but
   * that we should allow our own Spring Controller to define.
   */
  private boolean isForbiddenHeader(String headerName) {
    return "Content-Length".equalsIgnoreCase(headerName);
  }

}

package com.github.lilicaway.services.client.rest.httpUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;

import com.github.lilicaway.TestServiceClientConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestServiceClientConfig.class})
@WebAppConfiguration
public class HttpGenericHandlerTest {

  MockHttpServletResponse response = new MockHttpServletResponse();
  MockHttpServletRequest request = new MockHttpServletRequest();
  @Mock
  RequestWithEntity<String> reqWithEntity;
  @Autowired
  HttpGenericHandler handler;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testCallAndHandleHttp() throws IOException {
    ResponseEntity<String> responseEntity =
        new ResponseEntity<String>("someBody", setUpHeadersResponse(), HttpStatus.OK);
    setUpHeadersInRequest(request);
    when(reqWithEntity.call(any(HttpEntity.class))).thenReturn(responseEntity);
    assertEquals("someBody", handler.callAndHandleHttp(request, response, reqWithEntity));

    verify(reqWithEntity, Mockito.atLeastOnce()).call(any(HttpEntity.class));
    assertTrue(response.containsHeader("content-type"));
    assertTrue(response.containsHeader("Authorization"));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testCallAndHandleHttpWithException() throws IOException {
    setUpHeadersInRequest(request);
    when(reqWithEntity.call(any(HttpEntity.class)))
        .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "You have no power here.",
            setUpHeadersResponse(), new byte[0], Charset.defaultCharset()));
    assertNull(handler.callAndHandleHttp(request, response, reqWithEntity));

    verify(reqWithEntity, Mockito.atLeastOnce()).call(any(HttpEntity.class));
    assertTrue(response.containsHeader("content-type"));
    assertTrue(response.containsHeader("Authorization"));
    assertEquals(401, response.getStatus());
    assertEquals("You have no power here.", response.getErrorMessage());
  }

  @Test
  public void testCreateHttpEntityFromRequest() {
    setUpHeadersInRequest(request);
    HttpEntity<Void> httpEntityFromRequest = handler.createHttpEntityFromRequest(request);

    HttpHeaders headers = httpEntityFromRequest.getHeaders();
    assertNull(headers.get("host"));
    assertEquals(MediaType.APPLICATION_JSON, headers.getAccept().get(0));
    assertEquals("en-US", headers.get("accept-Language").get(0));
    assertEquals("application/json", headers.get("content-type").get(0));
  }

  @Test
  public void testAddHeadersToResponse() {
    HttpHeaders headers = setUpHeadersResponse();
    handler.addHeadersToResponse(headers, response);

    assertTrue(response.containsHeader("content-type"));
    assertTrue(response.containsHeader("Authorization"));
    assertEquals("application/json", response.getHeader("content-type"));
    assertEquals("Basic", response.getHeader("Authorization"));
  }

  private void setUpHeadersInRequest(MockHttpServletRequest req) {
    req.addHeader("host", "localhost:8090");
    req.addHeader("accept", "application/json");
    req.addHeader("accept-Language", "en-US");
    req.addHeader("content-type", "application/json");
    req.addHeader("Authorization", "Basic");
  }

  private HttpHeaders setUpHeadersResponse() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("content-type", "application/json");
    headers.add("Authorization", "Basic");
    return headers;
  }
}

package com.github.lilicaway.tiresias.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lilicaway.services.client.rest.httpUtils.HttpGenericHandler;
import com.github.lilicaway.services.client.rest.httpUtils.RequestWithEntity;
import com.github.lilicaway.tiresias.web.dto.ResponseResult;
import com.github.lilicaway.tiresias.web.service.OpenWeatherService;

@Controller
public class BaseController {

  private static int counter = 0;
  private static final String VIEW_INDEX = "index";

  @Autowired
  OpenWeatherService service;

  @Autowired
  HttpGenericHandler handler;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(ModelMap model) {

    model.addAttribute("message", "Welcome");
    model.addAttribute("counter", ++counter);

    return VIEW_INDEX;

  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String welcomeName(@PathVariable String name, ModelMap model) {

    model.addAttribute("message", "Welcome " + name);
    model.addAttribute("counter", ++counter);
    return VIEW_INDEX;
  }

  @ResponseBody
  @RequestMapping(value = "/weather/{cityName}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseResult displayWeather(@PathVariable String cityName, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    ResponseResult responseResult =
        handler.callAndHandleHttp(request, response, new RequestWithEntity<ResponseResult>() {
          @Override
          public ResponseEntity<ResponseResult> call(HttpEntity<Void> requestEntity) {
            ResponseEntity<ResponseResult> res =
                service.getOpenWeatherResponse(cityName, requestEntity);
            return res;
          }
        });
    return responseResult;
  }
}

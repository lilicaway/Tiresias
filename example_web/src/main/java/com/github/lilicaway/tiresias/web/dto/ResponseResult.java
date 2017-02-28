package com.github.lilicaway.tiresias.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseResult {

  Coord coord;
  List<Weather> weather = new ArrayList<>();
  String base;
  Main main;
  Wind wind;
  Clouds clouds;
  int dt;
  Sys sys;
  int id;
  String name;
  int cod;
  private Rain rain;

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public int getDt() {
    return dt;
  }

  public void setDt(int dt) {
    this.dt = dt;
  }

  public Sys getSys() {
    return sys;
  }

  public void setSys(Sys sys) {
    this.sys = sys;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCod() {
    return cod;
  }

  public void setCod(int cod) {
    this.cod = cod;
  }

  public Rain getRain() {
    return rain;
  }

  public void setRain(Rain rain) {
    this.rain = rain;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ResponseResult [coord=");
    builder.append(coord);
    builder.append(", weather=");
    builder.append(weather);
    builder.append(", base=");
    builder.append(base);
    builder.append(", main=");
    builder.append(main);
    builder.append(", wind=");
    builder.append(wind);
    builder.append(", clouds=");
    builder.append(clouds);
    builder.append(", dt=");
    builder.append(dt);
    builder.append(", sys=");
    builder.append(sys);
    builder.append(", id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append(", cod=");
    builder.append(cod);
    builder.append(", rain=");
    builder.append(rain);
    builder.append("]");
    return builder.toString();
  }

}

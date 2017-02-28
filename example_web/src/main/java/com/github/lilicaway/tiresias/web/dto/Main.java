package com.github.lilicaway.tiresias.web.dto;

public class Main {

  double temp;
  double pressure;
  long humidity;
  double temp_min;
  double temp_max;
  double sea_level;
  double grnd_level;

  public double getTemp() {
    return temp;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public double getPressure() {
    return pressure;
  }

  public void setPressure(double preassure) {
    this.pressure = preassure;
  }

  public long getHumidity() {
    return humidity;
  }

  public void setHumidity(long humidity) {
    this.humidity = humidity;
  }

  public double getTemp_min() {
    return temp_min;
  }

  public void setTemp_min(double temp_min) {
    this.temp_min = temp_min;
  }

  public double getTemp_max() {
    return temp_max;
  }

  public void setTemp_max(double temp_max) {
    this.temp_max = temp_max;
  }

  public double getSea_level() {
    return sea_level;
  }

  public void setSea_level(double sea_leve) {
    this.sea_level = sea_leve;
  }

  public double getGrnd_level() {
    return grnd_level;
  }

  public void setGrnd_level(double grnd_level) {
    this.grnd_level = grnd_level;
  }


}

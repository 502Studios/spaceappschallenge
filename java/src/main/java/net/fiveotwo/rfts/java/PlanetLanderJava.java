package net.fiveotwo.rfts.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import net.fiveotwo.rfts.core.PlanetLander;

public class PlanetLanderJava {

  public static void main(String[] args) {
    JavaPlatform.register();
    PlayN.run(new PlanetLander());
  }
}

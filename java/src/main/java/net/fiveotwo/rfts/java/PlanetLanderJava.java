package net.fiveotwo.rfts.java;

import playn.core.PlayN;
import com.leapmotion.*;
import com.leapmotion.leap.Controller;

import playn.java.JavaPlatform;

import net.fiveotwo.rfts.core.PlanetLander;

public class PlanetLanderJava {

  public static void main(String[] args) {

    /*
        Agregando un cambio en el archivo para poder hacer el pull request
    */

	LeapJava lp = new LeapJava();
    JavaPlatform platform = JavaPlatform.register();
    platform.setTitle("Planet Lander by 502Studios");
    playn.java.JavaPlatform.Config config = new playn.java.JavaPlatform.Config();
	  config.width = 800;
	  config.height = 480;
	  platform = JavaPlatform.register(config);
    platform.graphics().registerFont("PressStart2P", "Text/PressStart2P.ttf");
    PlayN.run(new PlanetLander());
  }
}

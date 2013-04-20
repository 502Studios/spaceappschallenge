package net.fiveotwo.rfts.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import net.fiveotwo.rfts.core.PlanetLander;

public class PlanetLanderHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("planetlander/");
    PlayN.run(new PlanetLander());
  }
}

package net.fiveotwo.rfts.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import net.fiveotwo.rfts.core.PlanetLander;

public class PlanetLanderActivity extends GameActivity {

  @Override
  public void main(){
	AndroidAccelerometer andaccel = new AndroidAccelerometer(this);
    PlayN.run(new PlanetLander(andaccel));
  }
}

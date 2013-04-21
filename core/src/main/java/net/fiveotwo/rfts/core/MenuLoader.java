package net.fiveotwo.rfts.core;

import net.fiveotwo.rfts.core.entities.Boton;
import net.fiveotwo.rfts.core.utils.Vector2;

import playn.core.Json;
import static playn.core.PlayN.*;

public class MenuLoader {
	Menu menu;
	Json.Object jso;
	public int x,y;
	public MenuLoader(Menu menu, String data) {
		this.menu = menu;
		this.jso = json().parse(data);
		menu.name = jso.getObject("metadata").getString("name");
		menu.upperlevel = jso.getObject("metadata").getString("upperlevel");
		loadButtons(jso.getArray("Buttons"));
	}

	public void loadButtons(Json.Array buttons) {
		x=(int)(40*3);
		y=(int)(40*3);
		for (int i = 0; i < buttons.length(); i++) {
			if ("true"
					.equals(storage().getItem(
							buttons.getObject(i).getString("previousLevel")
									+ "passed"))
					|| buttons.getObject(i).getString("previousLevel")
							.equals("none")||PlanetLander.godmode) {
				menu.buttons
						.add(new Boton(PlanetLander.Imagenes.get(buttons.getObject(i).getString("image")),
								PlanetLander.Imagenes.get(
										buttons.getObject(i).getString(
												"pressed_image")), 
												buttons.getObject(i).getString("text"),
								new Vector2(60,40),
								new Vector2(x,y)));
				if (buttons.getObject(i).getString("action")
						.equals("changelevel"))
					menu.buttons.get(i).gotolevel = buttons.getObject(i)
							.getString("destination");
				
				menu.buttons.get(i).setEnabled(true);
				
			} else {
				menu.buttons.add(new Boton(PlanetLander.Imagenes.get("lvllocked"), PlanetLander.Imagenes.get("lvllocked"), new Vector2(2*PlanetLander.format.font.size()+20,
								2*PlanetLander.format.font.size()), new Vector2(x,y)));
				menu.buttons.get(i).setEnabled(false);
			}
			x+=(int)(40*3);
			if(x>620){
				x=(int)(40*3);
				y+=(int)(40*2);
			}
		}
	}

	public void loadImages(Json.Array imagenes) {
		for (int i = 0; i < imagenes.length(); i++) {
			menu.imagenes.add(new MenuImage(assets().getImage(
					imagenes.getObject(i).getString("image")), imagenes
					.getObject(i).getInt("x"), imagenes.getObject(i)
					.getInt("y")));
		}
	}
}
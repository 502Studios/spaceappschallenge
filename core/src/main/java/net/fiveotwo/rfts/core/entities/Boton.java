package net.fiveotwo.rfts.core.entities;

import static playn.core.PlayN.graphics;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Surface;
import net.fiveotwo.rfts.core.PlanetLander;
import net.fiveotwo.rfts.core.utils.Rectangle;
import net.fiveotwo.rfts.core.utils.Vector2;

public class Boton {
	Rectangle bounds, localBounds;
	Vector2 posicion, dimensiones;
	public Image  norm, click, txt;
	public CanvasImage canvasi;
	private boolean enable = false;
	Vector2 Position = new Vector2(0, 0);
	public boolean isclicked, wasclicked;
	public String gotolevel;
	int fsize;
	ImageLayer img;
	boolean audible=true;
	
	public Boton() {
	}

	public Boton(Image normal, Image action, Image toshow, Vector2 dimensions,
			Vector2 position) {
		norm = normal;
		click = action;
		txt = toshow;
		dimensiones = dimensions;
		Position = position;
		int width = (int) (dimensions.X);
		int left = (int) (dimensions.X - width) / 2;
		int height = (int) (dimensions.Y);
		int top = (int) (dimensions.Y - height);
		localBounds = new Rectangle(left, top, width, height, 1);
		bounds = BoundingRectangle();
	}

	public Boton(Image normal, Image action, Vector2 dimensions,
			Vector2 position) {
		norm = normal;
		click = action;
		dimensiones = dimensions;
		Position = position;
		int width = (int) (dimensions.X);
		int left = (int) (dimensions.X - width) / 2;
		int height = (int) (dimensions.Y);
		int top = (int) (dimensions.Y - height);
		localBounds = new Rectangle(left, top, width, height, 1);
		bounds = BoundingRectangle();
	}

	public Boton(Image normal, Image action, String toshow, Vector2 dimensions,
			Vector2 position) {
		norm = normal;
		click = action;
		dimensiones = dimensions;
		Position = position;
		canvasi = graphics().createImage(
				(toshow.length()) * PlanetLander.format.font.size(),
				PlanetLander.format.font.size());
		canvasi.canvas().setFillColor(0xffffffff);
		canvasi.canvas().setStrokeWidth(2);
		canvasi.canvas().setStrokeColor(0xff000000);
		canvasi.canvas().fillText(graphics().layoutText(toshow, PlanetLander.format), 0,
				0);
		txt = canvasi;
		int width = (int) (dimensions.X);
		int left = (int) (dimensions.X - width) / 2;
		int height = (int) (dimensions.Y);
		int top = (int) (dimensions.Y - height);
		localBounds = new Rectangle(left, top, width, height, 1);
		bounds = BoundingRectangle();
	}

	public void setEnabled(boolean state) {
		this.enable = state;
	}

	public boolean isEnabled() {
		return this.enable;
	}

	public Vector2 Position() {
		return Position;
	}

	public Vector2 Dimension() {
		return this.dimensiones;
	}

	public void SetPosition(Vector2 positi) {
		Position = positi;
		bounds = BoundingRectangle();
	}

	public void Draw(Surface surf) {
		if (!isclicked) {
			if (norm != null)
				surf.drawImage(norm, Position().X, Position().Y, Dimension().X,
						Dimension().Y);
			if (txt != null)
				surf.drawImageCentered(txt, Position().X + Dimension().X / 2,
						Position().Y + Dimension().Y / 2);
		} else {
			if (click != null)
				surf.drawImage(click, Position().X, Position().Y,
						Dimension().X, Dimension().Y);
			if (txt != null)
				surf.drawImageCentered(txt, Position().X + 1 + Dimension().X
						/ 2, Position().Y + 1 + Dimension().Y / 2);
		}
	}
	public void Draw(Surface surf, float OffsetX, float OffsetY) {
		if (!isclicked) {
			if (norm != null)
				surf.drawImage(norm, Position().X+OffsetX, Position().Y+OffsetY, Dimension().X,
						Dimension().Y);
			if (txt != null)
				surf.drawImageCentered(txt, (Position().X+OffsetX) + Dimension().X / 2,
						(Position().Y+OffsetY) + Dimension().Y / 2);
		} else {
			if (click != null)
				surf.drawImage(click, Position().X+OffsetX, Position().Y+OffsetY,
						Dimension().X, Dimension().Y);
			if (txt != null)
				surf.drawImageCentered(txt, (Position().X+OffsetX)+ 1 + Dimension().X
						/ 2, (Position().Y+OffsetY) + 1 + Dimension().Y / 2);
		}
	}

	public Rectangle BoundingRectangle() {
		int left = (int) (Math.round(Position().X) + localBounds.Left);
		int top = (int) (Math.round(Position().Y) + localBounds.Top);
		return new Rectangle(left, top, localBounds.Width, localBounds.Height,
				1);
	}

	public boolean Touched(Rectangle r) {
		if (bounds.Intersects(r)) {
			if(isAudible()&&PlanetLander.activesound)
			PlanetLander.Sonidos.get("Buttonpress").play();
			isclicked = true;
		} else {
			isclicked = false;
		}
		return isclicked;
	}

	public boolean wasTouched(Rectangle r) {
		if (bounds.Intersects(r) && isclicked) {
			if(isAudible()&&PlanetLander.activesound)
			PlanetLander.Sonidos.get("ButtonOk").play();
			isclicked = false;
			return true;
		} else {
			isclicked = false;
			return false;
		}
	}
	public void setAudible(boolean ad){
		this.audible=ad;
	}
	boolean isAudible(){
		return this.audible;
	}
}

package net.fiveotwo.rfts.core.utils;

import net.fiveotwo.rfts.core.Map;


public class Camara {
	private Vector2 position;
	public boolean up, down,left,right;

	public Camara(Map level, Vector2 pos) {
		this.position = pos;
	}

	public Vector2 Position() {
		return this.position;
	}

	// manual camera
	public void setPosition(Vector2 value) {
		this.position = value;
	}

	public void update_position(float x, float y) {
		this.position.X += x;
		this.position.Y += y;
	}

	public void update_position(Vector2 pos) {
		this.position.X += pos.X;
		this.position.Y += pos.Y;
	}

	public void update() {

	}
}

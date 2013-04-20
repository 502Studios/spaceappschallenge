package net.fiveotwo.rfts.core.utils;

public class Circle {
	public float radius;
	public Vector2 center;

	public Circle(Vector2 center, float radius) {
		this.radius = radius;
		this.center = center;
	}

	public float getGrade(Vector2 point) {
		float grade;
		grade = (float) Math.atan2(point.Y - center.Y, point.X - center.X);
		grade = (float) Math.toDegrees(grade);
		// grade = grade*(float)(180/Math.PI);
		return grade;
	}

	public void setCenter(float x, float y) {
		this.center = new Vector2(x, y);
	}
}

package net.fiveotwo.rfts.core.utils;

public class Matematica {

	public static float Clamp(float x, float min, float max) {
		if (x < min) {
			return min;
		}
		if (x > max) {
			return max;
		}
		return x;
	}

}

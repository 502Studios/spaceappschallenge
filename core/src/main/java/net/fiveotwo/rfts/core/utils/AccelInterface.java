package net.fiveotwo.rfts.core.utils;

public interface AccelInterface {
	float getX();
	float getY();
	float getZ();
	void start();
	void pause();
	float[] getValues();
}

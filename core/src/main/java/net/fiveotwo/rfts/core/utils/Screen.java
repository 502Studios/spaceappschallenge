package net.fiveotwo.rfts.core.utils;

public abstract class Screen {

	// Identify each level
	public abstract String name();

	// Load the assets and everything needed for the screen
	public abstract void Init();

	// Runs the logic of the screen
	public abstract void Update(float delta);

	// Shuts down the current screen and pass to the next one
	public abstract void shutdown();

	// Paints the current screen
	public abstract void Draw(float delta);

}

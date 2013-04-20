package net.fiveotwo.rfts.core.utils;


import playn.core.Surface;

// Controls playback of an Animation.
public class AnimationPlayer {
	float frameIndex;
	private float time;
	public Animation animation;

	// obtenemos la animacion a animar (redundant).
	public Animation Animation() {
		return animation;
	}

	// cuadro actual.
	public float FrameIndex() {
		return frameIndex;
	}

	// obtenemos la textura de la spritesheet justo en el centro..
	public Vector2 Origin() {
		return new Vector2(Animation().FrameWidth() / 2.0f, Animation()
				.FrameHeight() / 2.0f);
	}

	// inicia o continua la animacion
	public void PlayAnimation(Animation animation) {
		/*
		 * Verifica que la animacion no este en playback actual, de lo contrario
		 * tendriamos un traslape de cuadros HORRIBLE
		 */
		if (Animation() == animation) {
			// cuenta el tiempo de animacion.

			return;
		} else {
			// inicia una animacion nueva.
			this.animation = animation;
			this.frameIndex = 0;
			this.time = 0.0f;
		}
	}

	public void updateAnimation(float gametime) {
		if (Animation() == null)
			return;

		time += gametime;
		while (time >= Animation().FrameTime()) {
			time -= Animation().FrameTime();
			// movemos al siguente cuadro, verificamos si loopea.
			if (Animation().IsLooping()) {
				frameIndex = (frameIndex + 1) % Animation().FrameCount();
			} else {
				frameIndex = Math.min(frameIndex + 1,
						Animation().FrameCount() - 1);
			}
		}
	}

	// avanza el tiempo, cambia a cuadro nuevo de cumplirse el tiempo del
	// actual.
	public void Draw(Surface surf, Vector2 position) {
		Rectangle source = new Rectangle(FrameIndex()
				* Animation().FrameWidth(), 0, Animation().FrameWidth(),
				Animation().FrameHeight(), 1);
		// dibuja el cuadro actual..
		float startX = position.X;
		float width = source.Width;
		surf.drawImage(Animation().Texture(), startX, position.Y, width,
				source.Height, source.Left, source.Top, source.Width,
				source.Height);
	}
}

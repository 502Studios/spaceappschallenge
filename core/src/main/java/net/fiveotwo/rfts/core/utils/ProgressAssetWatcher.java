package net.fiveotwo.rfts.core.utils;

import java.util.ArrayList;
import java.util.List;

import playn.core.Image;
import playn.core.PlayN;
import playn.core.util.Callback;

/**
 * @author bob
 *
 */
public class ProgressAssetWatcher implements Callback<Image> {

	private int loaded;
	private int errors;

	private boolean started;
	private final Listener listener;

	private List<Image> imagesToLoad;

	public ProgressAssetWatcher(Listener listener) {
		this.listener = listener;
		started = false;
		this.imagesToLoad = new ArrayList<Image>();
	}

	public void add(Image image) {
		imagesToLoad.add(image);
	}
	
	

	public boolean isDone() {
		return started && (loaded + errors == imagesToLoad.size());
	}

	public void start() {
		started = true;

		for (Image image : imagesToLoad) {
			image.addCallback(this);
		}
	}

	private void reportProgress() {
		if (listener != null) {
			int percentage = Math.round(((loaded + errors) / (float)imagesToLoad.size()) * 100);
			PlayN.log().debug("Internal Percentage: "+percentage);
			listener.progress(percentage);
		}
	}

	private void maybeDone() {
		reportProgress();
		if (isDone()) {
			if (listener != null) {
				listener.done();
			}
		}
	}

	@Override
	public void onSuccess(Image resource) {
		++loaded;
		maybeDone();
	}

	@Override
	public void onFailure(Throwable e) {
		++errors;
		if (listener != null)
			listener.error(e);
		maybeDone();
	}

	public interface Listener {
		void done();

		void progress(final int percentageComplete);

		void error(Throwable e);
	}

}
	


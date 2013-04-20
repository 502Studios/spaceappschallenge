package net.fiveotwo.rfts.core.utils;

import static playn.core.PlayN.json;
import static playn.core.PlayN.*;

import java.util.HashMap;



import playn.core.AssetWatcher;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.Json;


public class TexturePacker {
	public static HashMap<String, Image> Imagenes = new HashMap<String, Image>();
	public static AssetWatcher watcher;
	Image AtlasImage, PreImage;
	CanvasImage Canvas;
	String subpath;
	public TexturePacker(AssetWatcher watch, String path) {
		watcher=watch;
		this.subpath=path;
	}

	public HashMap<String, Image> ParseData(String data) {
		Json.Object datos = json().parse(data);
		if (datos.containsKey("meta")) {
			Json.Object metadata = datos.getObject("meta");
				if (metadata.containsKey("image")) {
					LoadAtlasImage(metadata.getString("image"));
				}				
				
		}
		if (datos.containsKey("frames")) {
			Json.Object frames = datos.getObject("frames");
			LoadSubImages(frames);
		}
		AtlasImage = null;
		PreImage = null;
		Canvas = null;
		return Imagenes;
	}

	void LoadSubImages(Json.Object frames) {
		Json.TypedArray<String> objects = frames.keys();
		for (int x = 0; x < objects.length(); x++) {
			Json.Object objeto = frames.getObject(objects.get(x));
			Json.Object dimension = objeto.getObject("frame");
			PreImage = AtlasImage.subImage(dimension.getNumber("x"), dimension.getNumber("y"), dimension.getNumber("w"), dimension.getNumber("h"));
			if(objeto.getBoolean("rotated")){
				Canvas = graphics().createImage(PreImage.width(), PreImage.height());
				Canvas.canvas().drawImage(PreImage,0,0);
				Canvas.canvas().rotate(1.570796327f);
				PreImage = Canvas;
			}
			Imagenes.put(objects.get(x), PreImage);
		}
	}

	void LoadAtlasImage(String path) {
		AtlasImage = assets().getImage(subpath+path);
		watcher.add(AtlasImage);
	}
}

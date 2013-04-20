package net.fiveotwo.rfts.core.utils;

import static playn.core.PlayN.graphics;


import java.util.List;

import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font;
import playn.core.Image;
import playn.core.Surface;
import playn.core.TextFormat;

public class TextBox {
	private CanvasImage frame;
	private CanvasImage timeFrame;
	private TextFormat tformat;
	private String text;
	private float marginx,marginy;
	private List<String> textList;
	private boolean done;
	private int count;
	private int textcounter =0;
	private float frameWidth, frameHeight, frameAlpha;
	private int frameColor;
	private Image image;

	/*
	 * This class takes care of drawing text boxes, with wichever format you
	 * want
	 */

	/*
	 * TextBox is the constructor, wich receives the size,alpha and color for
	 * the box.
	 */

	public TextBox(float frameWidth, float frameHeight, float frameAlpha,
			int frameColor) {
		marginx = 0;
		marginy = 0;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameAlpha = frameAlpha;
		this.frameColor = frameColor;
		timeFrame = graphics().createImage(50, 50);
		frame = graphics().createImage(frameWidth, frameHeight);
		frame.canvas().setAlpha(frameAlpha);
		frame.canvas().setFillColor(frameColor);
		frame.canvas().fillRect(0, 0, frame.canvas().width(),
				frame.canvas().height());
	}

	public TextBox(float frameWidth, float frameHeight, float frameAlpha,
			Image image) {
		this.frameWidth = frameWidth;
		count =0;
		this.frameHeight = frameHeight;
		this.frameAlpha = frameAlpha;
		this.image = image;
		frame = graphics().createImage(frameWidth, frameHeight);
		timeFrame = graphics().createImage(50, 50);
		frame.canvas().drawImage(image, 0, 0, frame.canvas().width(),
				frame.canvas().height());
	}

	/*
	 * setText will define the text,colors and position for the text in the box,
	 * not the format of the text itself.
	 */

	public void setText(String text, int textFillColor, int textStrokeColor,
			int textStrokeWidth) {
		frame.canvas().clear();
		if (this.image == null) {
			frame = graphics().createImage(this.frameWidth, this.frameHeight);
			frame.canvas().setAlpha(this.frameAlpha);
			frame.canvas().setFillColor(this.frameColor);
			frame.canvas().fillRect(0, 0, frame.canvas().width(),
					frame.canvas().height());
		} else {
			frame = graphics().createImage(this.frameWidth, this.frameHeight);
			frame.canvas().drawImage(this.image, 0, 0, frame.canvas().width(),
					frame.canvas().height());
		}
		this.text = text;
		frame.canvas().setFillColor(textFillColor);
		frame.canvas().setStrokeWidth(textStrokeWidth);
		frame.canvas().setStrokeColor(textStrokeColor);
		frame.canvas().fillText(graphics().layoutText(text, tformat),
				marginx, marginy);
		
	}
	
	public void getText(int textFillColor, int textStrokeColor,
			int textStrokeWidth) {
		if(this.textcounter<this.textList.size() || this.textcounter <= 0){
		frame.canvas().clear();
		if (this.image == null) {
			frame = graphics().createImage(this.frameWidth, this.frameHeight);
			frame.canvas().setAlpha(this.frameAlpha);
			frame.canvas().setFillColor(this.frameColor);
			frame.canvas().fillRect(0, 0, frame.canvas().width(),
					frame.canvas().height());
		} else {
			frame = graphics().createImage(this.frameWidth, this.frameHeight);
			frame.canvas().drawImage(this.image, 0, 0, frame.canvas().width(),
					frame.canvas().height());
		}
		this.text = this.textList.get(this.textcounter);
		this.textcounter++;
		frame.canvas().setFillColor(textFillColor);
		frame.canvas().setStrokeWidth(textStrokeWidth);
		frame.canvas().setStrokeColor(textStrokeColor);
		frame.canvas().fillText(graphics().layoutText(text, tformat),
				marginx, marginy);
		}else{
			done = true;
		}
	}
	


	/*
	 * setTextFormat will take care of setting the format for the text, this
	 * being the font to be use, the style of the text and the size
	 */

	public void setTextFormat(String font, Font.Style style, int size) {
		tformat = new TextFormat().withFont(graphics().createFont(font, style,
				size));
	}
	
	public boolean isDone(){
		return this.done;
	}
	
	public void setTextList(List<String> list){
		this.textList = list;
		done = false;
		this.textcounter = 0;
	}

	public void setTextWrapping(float wrapWidth) {
		tformat = tformat.withWrapWidth(wrapWidth-(marginx*2));
	}
	
	public void setMargins(float x,float y){
		marginx = x;
		marginy =y;
	}

	public void drawTextBox(Surface surf, Vector2 position) {
		count++;
		if(count >= 12){
			timeFrame.canvas().setFillColor(Color.rgb(255, 255, 255));
			timeFrame.canvas().fillText(graphics().layoutText("_", tformat),
					0, 0);
		}
		if(count >= 24){
			timeFrame.canvas().clear();
			count = 0;
		}
		surf.drawImage(frame, position.X, position.Y);
		surf.drawImage(timeFrame, position.X+frame.canvas().width()-marginx, position.Y+frame.canvas().height()-marginy);
	}
}

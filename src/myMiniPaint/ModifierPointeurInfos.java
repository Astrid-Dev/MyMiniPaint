package myMiniPaint;

import java.awt.Color;

public class ModifierPointeurInfos {

	private String type;
	private int pinceauSize;
	private Color PointeurColor;
	
	public ModifierPointeurInfos(String t, int pin, Color point)
	{
		this.type = t;
		this.pinceauSize = pin;
		this.PointeurColor = point;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getPinceauSize() {
		return this.pinceauSize;
	}
	
	public Color getPointeurColor() {
		return this.PointeurColor;
	}
}

package gui;
import processing.core.*;

public class Button extends GuiElement
{
	PApplet app;
	String label;
	boolean hasIcon;
	PImage img;
	int stroke, strokeHighlight;
	
	public Button(int x,int y,int w,int h,String label,Action myAction,PApplet app) {this(new PVector(x,y),w,h,label,myAction,app);}
	public Button(PVector pos, int w, int h, String label, Action myAction, PApplet app)
	{
		super(pos, w, h, myAction);
		this.app = app;
		stroke = app.color(0, 0, 110);
		strokeHighlight = app.color(0, 110, 240);
		
		hasIcon = (label.length() > 4 && label.substring(label.length()-4).equals(".png"));
		if(hasIcon) img = app.loadImage("icons\\" + label);
		this.label = label;
	}


	void update(Gui gui) 
	{
		gui.app.strokeWeight(2);
		gui.app.stroke(gui.overRect(this) ? strokeHighlight : stroke);
		if(hasIcon)
		{
			gui.app.noFill();
			gui.image(img, pos.x, pos.y, w, h);
			gui.rect(pos, w, h);
		}
		else
		{
			gui.fill(thisColor);
			gui.rect(pos, w, h);
			gui.textSize(h/3);
			gui.fill(app.brightness(thisColor) > 150 ? 0 : 255);
			gui.text(label, pos.x, pos.y);
		}
	}
	
	public void setHighlight(int color)
	{
		strokeHighlight = color;
	}
	public void setOutline(int color)
	{
		stroke = color;
	}
}

package gui;
import processing.core.*;

public class Slider extends GuiElement 
{
	float size;
	boolean horizontal, dragging;
	PVector end;
	float min, max, value, dialPos;
	
	public Slider(int x, int y, int l, float min, float max, Action myAction, boolean horizontal)
		{ this(new PVector(x,y), l, min, max, myAction, horizontal);}
	public Slider(PVector pos, int l, float min, float max, Action myAction, boolean horizontal)
	{
		super(pos, (horizontal ? l : 0), (horizontal ? 0 : -l), myAction);
		this.horizontal = horizontal;
		this.min = min;
		this.max = max;
		this.value = min;
		end = new PVector(pos.x+w, pos.y+h);
		dragging = false;
		thisColor = 200;
		size = 10;
	}
	
	void update(Gui gui) 
	{
		gui.app.strokeWeight(2);
		gui.app.stroke(0);
		gui.line(pos, end);
		gui.app.strokeWeight(1);
		gui.fill(thisColor);
		if(horizontal)
		{
			
			dialPos = PApplet.map(value, min, max, pos.x, pos.x + w);
			gui.circle(new PVector(dialPos, pos.y), size);
		}
		else
		{
			dialPos = PApplet.map(value, min, max, pos.y, pos.y + h);
			gui.circle(new PVector(pos.x, dialPos), size);
		}
	}
	
	public void setSize(float val)
	{
		size = val;
	}
	public void setValue(float val)
	{
		value = PApplet.constrain(val, min, max);
	}
	public void setValue(float mouseX, float mouseY)
	{
		float val;
		if(horizontal)
		{
			val = PApplet.map(mouseX, pos.x, pos.x + w, min, max);
		}
		else
		{
			val = PApplet.map(mouseY, pos.y, pos.y + h, min, max);
		}
		setValue(val);
	}
	
	public float getValue()
	{
		return value;
	}

}

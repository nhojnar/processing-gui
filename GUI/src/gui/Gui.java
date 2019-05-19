package gui;
import java.util.ArrayList;
import processing.core.*;

public class Gui 
{
	ArrayList<GuiElement> elements;
	int background;
	public PVector pos;
	public int h, w;
	PApplet app;
	
	public Gui(int x, int y, int w, int h, PApplet app) {this(new PVector(x,y), w, h, app);}
	public Gui(PVector pos, int w, int h, PApplet app)
	{
		this.pos = pos;
		this.w = w;
		this.h = h;
		this.app = app;
		background = app.color(0, 180, 130);
		elements = new ArrayList<GuiElement>();
	}
	
	public void update()
	{
		app.rectMode(PConstants.CORNER);
		fill(background);
		app.rect(pos.x, pos.y, w, h);
		for (GuiElement e : elements)
		{
			e.update(this);
		}
	}
	
	public void pressed()
	{
		for(GuiElement e : elements)
		{
			if(e instanceof Button && overRect(e))
				e.execute();
			if(e instanceof TextBox)
			{
				if(overRect(e))
					((TextBox) e).hasFocus = true;
				else if(((TextBox) e).hasFocus)
					((TextBox) e).hasFocus = false;
			}
			if(e instanceof Slider && overDial((Slider)e))
			{
				((Slider) e).dragging = true;
			}
		}
	}
	
	public void dragged()
	{
		for(GuiElement e : elements)
		{
			if(e instanceof Slider && ((Slider) e).dragging)
			{
				((Slider) e).setValue(app.mouseX - pos.x, app.mouseY - pos.y);
				e.execute();
			}
		}
	}
	
	public void released()
	{
		for(GuiElement e: elements)
		{
			if(e instanceof Slider && ((Slider) e).dragging)
				((Slider) e).dragging = false;
		}
	}
	
	public void typed(char key)
	{
		for (GuiElement e: elements)
		{
			if(e instanceof TextBox && ((TextBox) e).hasFocus) 
			{
				((TextBox) e).addCharacter(key);
			}
		}
	}
	
	public void addElement(GuiElement e)
	{
		elements.add(e);
	}
	public void addElements(GuiElement eArray[])
	{
		for(GuiElement e : eArray)
			elements.add(e);
	}
	public int removeElement(GuiElement e)
	{
		return elements.remove(e) ? 1 : -1;
	}
	
	public void rect(float x, float y, float w, float h)
	{
		app.rectMode(PConstants.CENTER);
		app.rect(x + pos.x, y + pos.y, w, h);
	}
	public void rect(PVector pos, int w, int h) {rect((int)pos.x, (int)pos.y, w, h);}
	void text(String s, float x, float y)
	{
		app.textAlign(PConstants.CENTER);
		app.text(s, x + pos.x, y + pos.y);
	}
	void textLeft(String s, float x, float y)
	{
		app.textAlign(PConstants.LEFT);
		app.text(s, x + pos.x, y + pos.y);
	}
	void fill(int i)
	{
		app.fill(i);
	}
	void fill(int r, int g, int b)
	{
		app.fill(r, g, b);
	}
	public void setBackground(int i)
	{
		background = i;
	}
	void textSize(float i)
	{
		app.textSize(i);
	}
	void line(float x, float y, float x2, float y2)
	{
		app.line(pos.x+x, pos.y+y, pos.x+x2, pos.y+y2);
	}
	void line(PVector pos1, PVector pos2)
	{
		line(pos1.x, pos1.y, pos2.x, pos2.y);
	}
	void circle(PVector pos, float size)
	{
		app.ellipseMode(PConstants.RADIUS);
		app.circle(pos.x + this.pos.x, pos.y + this.pos.y, size);
	}
	void image(PImage img, float x, float y, float w, float h)
	{
		app.imageMode(PConstants.CENTER);
		app.image(img, x + pos.x, y + pos.y, w, h);
	}
	
	boolean overRect(GuiElement e)
	{
		float adjustedX = e.pos.x + pos.x;
		float adjustedY = e.pos.y + pos.y;
		return (app.mouseX >= adjustedX - e.w/2 && app.mouseX <= adjustedX + e.w/2) && (app.mouseY >= adjustedY - e.h/2 && app.mouseY <= adjustedY + e.h/2);
	}
	boolean overCircle(PVector pos, float size)
	{
		return (PApplet.dist(pos.x + this.pos.x, pos.y + this.pos.y, app.mouseX, app.mouseY) < size);
	}
	boolean overDial(Slider s)
	{
		if(s.horizontal)
		{
			return overCircle(new PVector(s.dialPos, s.pos.y), s.size);
		}
		else
		{
			return overCircle(new PVector(s.pos.x, s.dialPos), s.size);
		}
	}
}

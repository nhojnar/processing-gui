package gui;
import processing.core.*;

public class Label extends GuiElement 
{
	String text;
	public Label(int x, int y, int h, String text) {this(new PVector(x,y), h, text);}
	public Label(PVector pos, int h, String text)
	{
		super(pos, 0, h);
		this.text = text;
		thisColor = 0;
	}
	void update(Gui gui) 
	{
		gui.fill(thisColor);
		gui.textSize(h);
		gui.text(text, pos.x, pos.y);
	}
	public void setLabel(String s)
	{
		text = s;
	}

}

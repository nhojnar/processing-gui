package gui;
import processing.core.*;

public abstract class GuiElement 
{
	public PVector pos;
	Action myAction;
	public int w, h;
	int thisColor;
	
	GuiElement(PVector pos, int w, int h, Action myAction)
	{
		this(pos, w, h);
		this.myAction = myAction;
	}
	GuiElement(PVector pos, int w, int h)
	{
		this.pos = pos;
		this.w = w;
		this.h = h;
		this.myAction = () -> {};
	}
	
	abstract void update(Gui gui);
	
	public void setColor(int color)
	{
		thisColor = color;
	}
	
	public void execute() {myAction.execute();}
	
}
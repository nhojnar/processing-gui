package gui;
import processing.core.*;

public class TextBox extends GuiElement
{
	final int TICKER_TIME = 16;
	String text;
	boolean hasFocus, showTicker;
	int tickerTimer;
	String textShown;
	
	public TextBox(PVector pos, int w, int h, Action myAction)
	{
		super(pos, w, h, myAction);
		hasFocus = false;
		text = "";
		thisColor = 255;
		showTicker = false;
		tickerTimer = 0;
	}
	public TextBox(int x,int y,int w,int h,Action myAction) {this(new PVector(x,y),w,h,myAction);}
	
	public void update(Gui gui)
	{
		gui.textSize(h*.75f);
		gui.fill(thisColor);
		gui.rect(pos, w, h);
		gui.fill(0);
		textShown = text;
		if(hasFocus)
		{
			tickerTimer++;
			if(tickerTimer > TICKER_TIME)
				showTicker = true;
			if(tickerTimer > TICKER_TIME * 2)
			{
				tickerTimer = 0;
				showTicker=  false;
			}

			while(gui.app.textWidth(textShown) > w)
			{
				textShown = textShown.substring(1);
			}
			gui.textLeft(textShown, pos.x-w/2 + 3, pos.y+h/4);
			
			if(showTicker)
			{
				gui.app.strokeWeight(1);
				gui.line(pos.x - w/2 + 3 + gui.app.textWidth(textShown), pos.y - h/2 + 3, pos.x - w/2 + 3 + gui.app.textWidth(textShown), pos.y + h/2 - 3);
			}
		}
		else
		{
			while(gui.app.textWidth(textShown) > w)
			{
				textShown = textShown.substring(0,textShown.length()-1);
			}
			gui.textLeft(textShown, pos.x - w/2 + 3, pos.y + h/4);
		}
	}
	
	public String getText()
	{
		return text;
	}
	public void setText(String s)
	{
		text = s;
	}
	
	public void addCharacter(char c)
	{
		if(c == PConstants.ENTER)
			execute();
		else if(c == PConstants.BACKSPACE && text.length() > 0)
			text = text.substring(0, text.length()-1);
		else if(Character.isAlphabetic(c) || Character.isDigit(c))
			text += c;
	}
	
	public void clear()
	{
		text = "";
	}
	
	
}

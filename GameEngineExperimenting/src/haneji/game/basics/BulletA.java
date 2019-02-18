package haneji.game.basics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BulletA extends Entity {

	public BulletA(ArrayList<Entity> e, Entity parent)
	{
		super(e);
		width = height = 10;
		x = ( parent.x + (parent.getWidth() / 2) ) - (width / 2);
		y = parent.y + parent.getWidth();
		dy = 4;
	}
	
	@Override
	public void draw(Graphics g) {
		if (phase==-1) return;
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
	}

}

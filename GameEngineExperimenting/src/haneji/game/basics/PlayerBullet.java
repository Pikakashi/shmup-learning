package haneji.game.basics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PlayerBullet extends Entity {

	public PlayerBullet(ArrayList<Entity> e, Entity parent)
	{
		super(e);
		width = 5;
		height = 10;
		x = ( parent.x + (parent.getWidth() / 2) ) - (width / 2);
		y = parent.y - height;
		dy = -18;
	}
	
	public void update()
	{
		if (phase==-1) return; 
		ArrayList<Entity> temp = new ArrayList<Entity>(entities);
		
		for (Entity e : temp)
		{
			if (e instanceof EnemyA)
			{
				if (collisionCheck(e))
				{
					entities.get(temp.indexOf(e)).kill();
					kill();
				}
			}
		}
		super.update();
		
	}
	
	@Override
	public void draw(Graphics g) {
		if (phase==-1) return;
		g.setColor(Color.CYAN);
		g.fillOval(x, y, width, height);
	}

}

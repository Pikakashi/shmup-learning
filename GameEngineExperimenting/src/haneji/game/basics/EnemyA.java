package haneji.game.basics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyA extends Entity {

	public EnemyA(ArrayList<Entity> e, int xPos, int yPos)
	{
		super(e);
		x = xPos;
		y = yPos;
		width = height = 28;
		dx = 4;
	}
	
	@Override
	public void draw(Graphics g) {
		if (phase==-1) return;
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
	public void update()
	{
		if (phase==-1) return;
		if (x>1000 || x < 80)
		{
			dx = -1 * dx;
		}
		if (age%6==0 && age%36>17)
		
		{
			entities.add(new BulletA(entities, this));
		}
		
		ArrayList<Entity> temp = new ArrayList<Entity>(entities);
		
		for (Entity e : temp)
		{
			if (e instanceof PlayerBullet)
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

}

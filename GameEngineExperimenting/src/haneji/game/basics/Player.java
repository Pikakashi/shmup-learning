package haneji.game.basics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity {

	private InputHandler input;
	private int mod, cooldown;
	
	public Player(ArrayList<Entity> e, InputHandler input)
	{
		super(e);
		this.input = input;
		width = height = 8;
	}
	
	public void update() {
		if (phase == 0)
		{
			dx = dy = 0;
			if (input.isKeyDown(KeyEvent.VK_SHIFT))
			{
				mod = 3;
			}
			else
			{
				mod = 0;
			}
			if (input.isKeyDown(KeyEvent.VK_RIGHT))
			{
				dx += 5 - mod;
			}
			if (input.isKeyDown(KeyEvent.VK_LEFT))
			{
				dx -= 5 - mod;
			}
			if (input.isKeyDown(KeyEvent.VK_DOWN))
			{
				dy += 5 - mod;
			}
			if (input.isKeyDown(KeyEvent.VK_UP))
			{
				dy -= 5 - mod;
			}
			if (input.isKeyDown(KeyEvent.VK_SPACE))
			{
				if (cooldown<=0)
				{
					entities.add(new PlayerBullet(entities, this));
					cooldown=3;
				}
			}
		}
		cooldown--;
		
		ArrayList<Entity> temp = new ArrayList<Entity>(entities);
		
		for (Entity e : temp)
		{
			if (e instanceof BulletA)
			{
				if ( collisionCheck(e) )
				{
					entities.get(temp.indexOf(e)).kill();
					phase = 1;
				}
			}
		}
		super.update();

	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		if (phase == 1) g.setColor(Color.GRAY);
		g.fillOval(x, y, width, height);
	}

}

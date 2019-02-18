package haneji.game.basics;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Entity
{
	protected ArrayList<Entity> entities;
	public int x, y, dx, dy;
	protected int width, height, age, phase;
	
	public Entity(ArrayList<Entity> entities)
	{
		x = y = dx = dy = age = phase = 0;
		width = height = 1;
		this.entities = entities;
		//entities.add(this);
	}

	public void kill()
	{
		phase = -1;
		//entities.remove(this);
	}
	
	public boolean collisionCheck(Entity e)
	{
		return ( x >= e.x && x <= e.x + e.getWidth() &&
				y >= e.y && y <= e.y + e.getHeight() ) ||
					( x + width >= e.x && x + width <= e.x + e.getWidth() &&
					y + height >= e.y && y + height <= e.y + e.getHeight() );
	}
	
	public void update()
	{
		if (phase==-1) return;
		x += dx;
		y += dy;
		age++;
	}
	
	public abstract void draw(Graphics g);
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getAge() {
		return age;
	}

	public int getPhase() {
		return phase;
	}
	
	
	
}

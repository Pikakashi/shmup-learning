package haneji.game.basics;

import java.awt.*;
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/** notes
 * backwards for loops so i can delete things as i go
 * add layers as another variable for entities
 * all entity constructors should have either array parent or array x y
 * @author Kali
 *
 */

public class GameWindow extends JFrame {
	
	boolean isRunning;
	int fps = 30;
	int windowWidth = 1080;
	int windowHeight = 720;
	
	BufferedImage backBuffer;
	Insets insets;
	InputHandler input;
	Player p;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	
	public static void main(String[] args)
	{
		GameWindow game = new GameWindow();
		game.run();
		System.exit(0);
	}
	
	public void run()
	{
		initialize();
		while(isRunning)
		{
			long time = System.currentTimeMillis();
			update();
			draw();
			time = (1000 / fps) - (System.currentTimeMillis() - time);
			if (time > 0)
			{
				try
				{
					Thread.sleep(time);
				}
				catch(Exception e) {}
			}
		}
		setVisible(false);
	}
	
	void initialize()
	{
		isRunning = true;
		
		setTitle("Game Tutorial");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		insets = getInsets(); 
		setSize(insets.left + windowWidth + insets.right, 
		                insets.top + windowHeight + insets.bottom); 
		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		p = new Player(entities, input);
		p.x = windowWidth / 2;
		p.y = windowHeight * 2 / 3;
		entities.add(p);
		//entities.add(new Player(entities, input));
		entities.add(new EnemyA(entities, 100, 100));
		entities.add(new EnemyA(entities, 400, 150));
		entities.add(new EnemyA(entities, 1000, 200));
		entities.add(new EnemyA(entities, 800, 250));
		
		//entities.add(new Enemy(30,30,p));
	}
	
	void update()
	{
		ArrayList<Entity> temp = new ArrayList<Entity>(entities);
		
		if ( !temp.isEmpty() )
		{
			for ( Entity e : temp )
			{
				e.update();
				if (e!=p)
				{
					if ( e.x > windowWidth + 50 || e.x < -50 || 
						e.y > windowHeight + 50 || e.y < -50 )
					{
						e.kill();
					}
				}
			}
		}
		
		for (int i = entities.size() - 1; i >=0; i--)
		{
			if (entities.get(i).getPhase()==-1)
			{
				entities.remove(i);
			}
		}
	}
	
	void draw()
	{
		Graphics g = getGraphics();
		
		Graphics bbg = backBuffer.getGraphics();
		
		bbg.setColor(Color.BLACK);
		bbg.fillRect(0, 0, windowWidth, windowHeight);
				
		if ( !entities.isEmpty() )
		{
			for ( Entity e : entities )
			{
				e.draw(bbg);
			}
		}
		
		g.drawImage(backBuffer,  insets.left,  insets.top, this);
	}

}

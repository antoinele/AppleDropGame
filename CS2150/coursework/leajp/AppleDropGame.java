package coursework.leajp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class AppleDropGame {
	/**
	 * Game area width = 500
	 *           height = 500
	 * 
	 * This isn't the rendering size
	 */
	
	public final float gameWidth = 500f;
	public final float gameHeight = 500f;
	
	public final float droppedBoundary = 10f;
	
	public final float appleWidth = 2f;
	public final float appleHeight = 2f;
	
	public final float basketWidth = 60f;
	public final float basketHeight = 6f;
	
	public final float basketBoundary = 10f;
	
	private long score = 0; // using a long for the score is way too optimistic
	private int lives = 5;
	
	private float basketPos = 0;
	
	private Random rng;
	
	private long time;
	private long timeDelta;
	
	public class FallingApple
	{
		public final float fallRate = 30f; // units/s
		
		public float x;
		public float y;
		
		public boolean dropped = false;
		
		public FallingApple()
		{
			x = rng.nextInt((int)((gameWidth - (2*basketBoundary))/50)) * 50 + basketBoundary;
			y = gameHeight * 1.1f; // start just above the top of the screen
		}
		
		public void fall()
		{
			y -= fallRate * (timeDelta / 1000f);
		}
	}
	
	public List<FallingApple> fallingApples = new ArrayList<FallingApple>(20);
	
	private long lastDrop = 0;
	
	public AppleDropGame()
	{
		rng = new Random();
	}
	
	private void spawnApple()
	{
		FallingApple fa = new FallingApple();
		
		fallingApples.add(fa);
	}
	
	private void handleSpawn()
	{
		if((time / 1000) != lastDrop)
		{
			spawnApple();
			
			lastDrop = time / 1000;
		}
	}
	
	private void handleGravity()
	{
		for(FallingApple a : fallingApples)
		{
			a.fall();
		}
	}
	
	private void handleCatch()
	{
		FallingApple[] toRemove = new FallingApple[fallingApples.size()];
		int removeidx = 0;
		
		for(FallingApple a : fallingApples)
		{
			if((a.y < droppedBoundary + basketHeight) && ((a.x > basketPos - (basketWidth / 2)) && (a.x < basketPos + (basketWidth / 2))))
			{
				score += 200;
				
				toRemove[removeidx++] = a;
			}
		}
		
		for(int i=0; i<removeidx; i++)
		{
			fallingApples.remove(toRemove[i]);
		}
	}
	
	private void handleDropped()
	{
		FallingApple[] toRemove = new FallingApple[fallingApples.size()];
		int removeidx = 0;
		
		for(FallingApple a : fallingApples)
		{
			if(!a.dropped && a.y < droppedBoundary)
			{
				//score penalty
				score -= 100;
				
				//lose a life
				lives--;
				
				a.dropped = true;
			}
			
			if(a.y < -(gameHeight * 0.1f)) //Off of the bottom of the screen
			{
				toRemove[removeidx++] = a;
			}
		}
		
		for(int i=0; i<removeidx; i++)
		{
			fallingApples.remove(toRemove[i]);
		}
	}
	
	public void update()
	{
		long newtime = System.currentTimeMillis();
		timeDelta = newtime - time;
		time = newtime;
		
		handleSpawn();
		handleGravity();
		handleCatch();
		handleDropped();
	}
	
	public long getScore()
	{
		return score;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public float getBasketPos()
	{
		return basketPos;
	}
	
	public void moveBasket(float pos)
	{
		basketPos = pos;
//		basketPos = gameWidth * ((pos + 1)/2);
		if(basketPos < basketBoundary)
		{
			basketPos = basketBoundary;
		}
		else if(basketPos > (gameWidth - basketBoundary))
		{
			basketPos = (gameWidth - basketBoundary);
		}
	}
}

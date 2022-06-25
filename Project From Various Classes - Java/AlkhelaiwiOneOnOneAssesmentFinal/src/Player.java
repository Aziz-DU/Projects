import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.introcs.Draw;


public class Player{
	int playerXPos=GameState.CANVAS_SIZE/2;
	int playerYPos=GameState.CANVAS_SIZE/2;
	static int Velocity=3;
	int radius=20;
	private Color color; 
	int playerHealth;
	int tookDamage=0;
	int ateFood=0;
	int difficultyLevel;
	int damageAmount=15;

	public Player() {
	//	GameState();
	  color=Draw.BLACK;	//initializing the color variable at first to be controlled later
	}
    public void draw(){
    	GameState.draw.setPenColor(color);
    	GameState.draw.filledRectangle(playerXPos,playerYPos,radius,radius);
	  }

    public void grow() {	//when the player collects food, increase radius
      radius+=10;
    }
    public void shrink() {   
      for(int i=0; i<damageAmount;i++) { //when the player gets hit, decrease radius incrementally so it wouldn't go below 0 and crash the game
    	if(radius>0) {
    		radius-=1;
        	}	
    	}
    }
    public boolean dead() {
      if(radius<=0) {		 //when it gets to 0 the dead function tells the gameplay state to call lost()
    	  return true;
    	}
      return false;
    }

    public void setRandomColor()		//cycle through different colors for the player, it looks cooler.
    {
     Random rgen = new Random();		//using random instead of math.random, i find it more readable
     int colorSelector = rgen.nextInt(5);    
     switch (colorSelector)
    {
    case 0:
    color=Draw.PINK;
    break;
    case 1:
    color=Draw.BLUE;
    break;
    case 2:
    color=Draw.GREEN;
    break;
    case 3:
    color=Draw.YELLOW;
    break;
    case 4:
    color=Draw.CYAN;
    break;
    }    
    }
}

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class GameState{
	enum Difficulty {EASY, NORMAL, HARD};
    public static final int CANVAS_SIZE = 800; // made the variable static to access it from different classes... 
	boolean mainMenu;						//...without the need for creating an instance of this class there
	boolean gamePlayState;
	boolean lost;
	boolean won;						//different booleans of different stages of the game that the CheckGameState() method uses
	Player player = new Player();		//instance of the classes to access it's variables and methods
	Food food = new Food();
	boolean callOnce=true;				// controls CheckGameState() to have it only call gameplay stage once (since it's in the driver while loop)
	protected Difficulty difficulty;
	boolean Active=true;
	Enemy[] recList;
	Food[] foodlist;
	int numberOfAttempts=1;
    int spawnNumber;	
    boolean dropPowerUps;
    boolean alreadySpawned;
    boolean justStarted=true;		// boolean to make the player untouchable until he starts moving.
	public GameState(){
	StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
	StdDraw.setXscale(0, CANVAS_SIZE);
	StdDraw.setYscale(0, CANVAS_SIZE);
	mainMenu=true;
	gamePlayState=false;
	lost=false;
	won=false;	
	}
	
	public void CheckGameState() {    // this method controls which stage of the game the player is in, called on driver while loop
		if(mainMenu) {
			MainMenu();
		}
		else if(gamePlayState) {
			if(callOnce) {
				GamePlayState();
				callOnce=false;
			}
		}
		else if(lost) {
			Lost();
		}
		else if(won) {
			Won();
		}
	}
	
	public void MainMenu() {

		double mouseY= StdDraw.mouseY();	//we only need the mouseY position for the menu
		StdDraw.line(0, 220, 800, 220);
		StdDraw.line(0, 470, 800, 470);
		StdDraw.line(0, 700, 800, 700);
	    StdDraw.setFont(new Font("msyh", Font.BOLD, 40));
		StdDraw.text(400, 750,"Pick a level of difficulty" );
	    StdDraw.setFont(new Font("msyh", Font.BOLD, 70));
		StdDraw.text(400, 570,"EASY" );
		StdDraw.text(400, 350,"NORMAL" );
		StdDraw.text(400, 100,"HARD" );					//simple ui that lets the user pick the difficulty 
													    //level and adjusts related variable based on the selection
		if(StdDraw.isMousePressed()) {
			if(mouseY<700 && mouseY>470) {
				setDifficulty(Difficulty.EASY);		//setting difficulty level to selection
				gamePlayState=true;
				mainMenu=false;						//telling the CheckGameState() method that we're done with main menu, jump to game stage
			}
			else if(mouseY<465 && mouseY>227) {
				setDifficulty(Difficulty.NORMAL);
				gamePlayState=true;
				mainMenu=false;
			}
			else if(mouseY<215 && mouseY>5) {
				setDifficulty(Difficulty.HARD);
				gamePlayState=true;
				mainMenu=false;
			}
		}

		StdDraw.show();
		StdDraw.pause(10);
  }
	public void GamePlayState() {
		
		   if(difficulty==Difficulty.EASY) {    			//adjusting few variables based on difficulty
			   spawnNumber=5;  
			   player.damageAmount=10;
		   }
		   else if(difficulty==Difficulty.NORMAL) {
			   spawnNumber=7;   
			   player.damageAmount=15;
		   }
		   else if(difficulty==Difficulty.HARD) {
			   spawnNumber=10;   
			   player.damageAmount=20;
		   }

			Enemy[] recList = new Enemy[spawnNumber];
			Food[] foodlist = new Food[spawnNumber];
			
		   Random rgen = new Random();
		   PowerUps powerUps=new PowerUps(); 
		   int xpowerUps = rgen.nextInt(CANVAS_SIZE);
		   int ypowerUps = rgen.nextInt(CANVAS_SIZE);
		   int powerUpsSize = rgen.nextInt(8)+12;
		   for(int i = 0; i<spawnNumber; i++)
		   {
		   int xEnemy = rgen.nextInt(CANVAS_SIZE);
		   int yEnemy = rgen.nextInt(CANVAS_SIZE);
		   int xFood = rgen.nextInt(CANVAS_SIZE);
		   int yFood = rgen.nextInt(CANVAS_SIZE);
		   int enemyHeight = rgen.nextInt(10)+50;
		   int enemyWidth = rgen.nextInt(10)+50;
		   int foodSize = rgen.nextInt(10)+10;
		   int xVel = rgen.nextInt(5)+5;
		   int yVel = rgen.nextInt(3)+5;
		   recList[i] = new Enemy(xEnemy,yEnemy,enemyHeight,enemyWidth,xVel,yVel); 			//creating instances based on the spawnNumber
		   foodlist[i] = new Food(xFood,yFood,foodSize,foodSize);

		   }
		   StdDraw.enableDoubleBuffering();

	       while(Active) {

			   StdDraw.clear();
			   for(int i = 0; i<spawnNumber; i++)
			{
			recList[i].draw();
			recList[i].move();	
			foodlist[i].draw();
			if(dropPowerUps&&(!alreadySpawned)) {
			    StdDraw.setFont(new Font("msyh", Font.BOLD, 10));
				powerUps= new PowerUps(xpowerUps,ypowerUps,powerUpsSize,powerUpsSize);
				powerUps.draw();
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(xpowerUps, ypowerUps,"PowerUp!" );
			}
			if(recList[i].isIntersecting(player)&&(!justStarted))		//if the player touched one of the enemies, deal damage,shrink and take it out of the screen
			{
			   player.shrink();
			   recList[i].xCoord=-900;
			   player.tookDamage++;
			}
			if(foodlist[i].isIntersecting(player)&&(!justStarted))
			{
			  player.grow();
			  foodlist[i].xCoord=-900;  
			  player.ateFood++;
			   int chanceOfDropping = rgen.nextInt(3);
			   if(chanceOfDropping==2) {	//just making it 33% chance that a powerup will drop
					  dropPowerUps=true;   
			   }
			}

			}
			if(powerUps.isIntersecting(player)&&(!alreadySpawned)) {
				System.out.println("Increased velocity!");
				powerUps.ActivatePowerUp();
				xpowerUps-=900;
				alreadySpawned=true;
			}
			player.draw();
			player.setRandomColor();		//drawing the player square and making it look....cooler?
			if (StdDraw.isKeyPressed(KeyEvent.VK_UP)&&player.playerYPos<CANVAS_SIZE-10){
			   player.playerYPos+= Player.Velocity;
			   justStarted=false;
			  }
			else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)&&player.playerYPos>10){
			  player.playerYPos -= Player.Velocity;
			  justStarted=false;

			  }
			else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)&&player.playerXPos>10){
			  player.playerXPos -= Player.Velocity;
			  justStarted=false;

			  }
			else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)&&player.playerXPos<CANVAS_SIZE-10){
			  player.playerXPos += Player.Velocity;
			  justStarted=false;
			  }
		    StdDraw.show();  
		    StdDraw.pause(20);
		    if(player.dead()) {			//if the player radius becomes less than 0, they die... they loose radius when they touch enemies
			      lost=true;
			      gamePlayState=false;	//telling CheckGameState() to go to the lost method
			      StdDraw.clear();
			      Active=false;
			  }
			   if(player.ateFood>=spawnNumber) { //if the player collects all the food they win
			      won=true;
			      gamePlayState=false;
			      StdDraw.clear();
			      Active=false; 
			  }
	       }
	   }
	
	public void Lost() {
		StdDraw.setPenColor(StdDraw.BLACK);
		double mouseY= StdDraw.mouseY();
		StdDraw.line(0, 220, 800, 220);
		StdDraw.line(0, 470, 800, 470);
		StdDraw.line(0, 700, 800, 700);
	    StdDraw.setFont(new Font("msyh", Font.BOLD, 70));
		StdDraw.text(400, 750,"You Lost!" );
	    StdDraw.setFont(new Font("msyh", Font.BOLD, 40));
		StdDraw.text(400, 570,"Play again" );
		StdDraw.text(400, 350,"Print a performance summary" );
		StdDraw.text(400, 100,"Close" );
		
		if(StdDraw.isMousePressed()) {
			if(mouseY<700 && mouseY>470) {
				ResetStats();	//same logic of mainmenu, except i reset all needed variables before going to the GamePlayState()
				callOnce=true;	//reseting this so GamePlayState() only gets called once again.
				lost=false;
				Active=true;
				numberOfAttempts++;	//just saves how many tries the player has for the summary print.
				gamePlayState=true;
			}
			else if(mouseY<465 && mouseY>227) {
				PrintSummary();		//upon hitting the print performance summary, show that it was printed and call the print method
				StdDraw.text(400, 270,"Done!!!" );
			}
			else if(mouseY<215 && mouseY>5) {
				System.exit(0);		//bye?
			}
		}
		StdDraw.show();
		StdDraw.pause(10);
  }
	public void Won() {
		  StdDraw.setPenColor(StdDraw.BLACK);
			double mouseY= StdDraw.mouseY();
			StdDraw.line(0, 220, 800, 220);
			StdDraw.line(0, 470, 800, 470);
			StdDraw.line(0, 700, 800, 700);
		    StdDraw.setFont(new Font("msyh", Font.BOLD, 70));
			StdDraw.text(400, 750,"You won!!!!" );
		    StdDraw.setFont(new Font("msyh", Font.BOLD, 40));
			StdDraw.text(400, 570,"Play again" );
			StdDraw.text(400, 350,"Print a performance summary" );
			StdDraw.text(400, 100,"Close" );	//same as the lost state except it shows that you won
			
			if(StdDraw.isMousePressed()) {
				if(mouseY<700 && mouseY>470) {
					ResetStats();
					callOnce=true;
					lost=false;
					Active=true;
					numberOfAttempts++;
					gamePlayState=true;
				}
				else if(mouseY<465 && mouseY>227) {
					PrintSummary();
					StdDraw.text(400, 270,"Done!!!" );
				}
				else if(mouseY<215 && mouseY>5) {
					System.exit(0);
				}
			}
		   StdDraw.show();
		   StdDraw.pause(10);
	}
	public void ResetStats() {  //reset important variables before we start the game again
		player.playerXPos=CANVAS_SIZE/2;
		player.playerYPos=CANVAS_SIZE/2;
		Player.Velocity=10;
    	player.radius=20;
    	player.tookDamage=0;
    	player.ateFood=0;
    	dropPowerUps=false;
    	justStarted=true;
    	alreadySpawned=false;
	}
	public void PrintSummary() {
		String attempt="st";

		 if(numberOfAttempts==2) {
			attempt="nd";
		}
		else if(numberOfAttempts==3) {
			attempt="rd";
		}
		else if(numberOfAttempts>=4) {
			attempt="th";
		}
		File file = new File("performanceSummary.txt");		//prints out info about the players performance
		try {
			PrintWriter fileOut = new PrintWriter(file);
			fileOut.println("This round, you collected "+player.ateFood+" squares.");
			fileOut.println("You got hit "+player.tookDamage+" times.");
			fileOut.println("You played in "+difficulty+" mode.");
			fileOut.println("This is your "+numberOfAttempts+ attempt+" attempt.");
			fileOut.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
	}
	  public Difficulty getDifficulty() {		//getter and setter to control the selected difficulty
		    return difficulty;	    
		}
		public void setDifficulty(Difficulty diff) {
		    difficulty=diff;   
		}
	}
	


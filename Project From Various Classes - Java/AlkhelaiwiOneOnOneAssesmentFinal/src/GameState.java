import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;
import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.DrawListener;

public class GameState implements DrawListener{
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
	int numberOfAttempts=1;
    int spawnNumber;	
    boolean powerUpVel;					//boolean for the increased velocity power up
    boolean powerUpKillEnemy;			//boolean for the click at 3 enemies power up	
    boolean alreadySpawnedVel;			//controls the power ups to only allow one drop of each
    boolean alreadySpawnedKill;
    int powerUpKillAmount=0;
    boolean justStarted=true;		// boolean to make the player untouchable until he starts moving.
    public static Draw draw;		//static to access it from other classes
    private ArrayList<Food> foodArr=new ArrayList<>();		//arraylist for controlling the draws of enemies and food square 
    private ArrayList<Enemy> enemyArr=new ArrayList<>();
    private int wave=1;				//used to calculate and print current wave to the screen plus controls changing waves
    private int foodEatenPerWave=0;	//used to change waves
    public static boolean killEnemyByClicking=false;	//power ups class uses it
    
	public GameState(){
	draw=new Draw();
	draw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
	draw.setXscale(0, CANVAS_SIZE);
	draw.setYscale(0, CANVAS_SIZE);
	mainMenu=true;
	gamePlayState=false;
	lost=false;
	won=false;	
	draw.addListener(this);

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

		double mouseY= draw.mouseY();	//we only need the mouseY position for the menu
		draw.line(0, 220, 800, 220);
		draw.line(0, 470, 800, 470);
		draw.line(0, 700, 800, 700);
	    draw.setFont(new Font("msyh", Font.BOLD, 40));
		draw.text(400, 750,"Pick a level of difficulty" );
	    draw.setFont(new Font("msyh", Font.BOLD, 70));
		draw.text(400, 570,"EASY" );
		draw.text(400, 350,"NORMAL" );
		draw.text(400, 100,"HARD" );					//simple ui that lets the user pick the difficulty 
													    //level and adjusts related variable based on the selection
		if(draw.isMousePressed()) {
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

		draw.show();
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
		   player.damageAmount=18;
	   }

	   Random rgen = new Random();
	   PowerUps powerUpVelocity=new PowerUps(); 
	   PowerUps powerUpEnemyKiller=new PowerUps(); 
	   int velPowerUpX = rgen.nextInt(CANVAS_SIZE);
	   int velPowerUpY = rgen.nextInt(CANVAS_SIZE);
	   int killPowerUpX = rgen.nextInt(CANVAS_SIZE);
	   int killPowerUpY = rgen.nextInt(CANVAS_SIZE);	   
	   int powerUpsSize = rgen.nextInt(8)+12;
	   
	   for(int i = 0; i<spawnNumber; i++){	//for loop for drawing enemies and food 
		   int xEnemy = rgen.nextInt(CANVAS_SIZE);
		   int yEnemy = rgen.nextInt(CANVAS_SIZE);
		   int xFood = rgen.nextInt(CANVAS_SIZE);
		   int yFood = rgen.nextInt(CANVAS_SIZE);
		   int enemyHeight = rgen.nextInt(10)+50;
		   int enemyWidth = rgen.nextInt(10)+50;
		   int foodSize = rgen.nextInt(10)+10;
		   int xVel = rgen.nextInt(2)+3;
		   int yVel = rgen.nextInt(1)+3;
		   enemyArr.add(new Enemy(xEnemy,yEnemy,enemyHeight,enemyWidth,xVel,yVel));
		   foodArr.add(new Food(xFood, yFood, foodSize, foodSize));
	   }
	   
	   draw.enableDoubleBuffering();
			
       while(Active) {
		   draw.clear();
    	   drawArr();
    	   String waveText=String.valueOf(wave);		//drawing current wave to the screen
	   	   draw.setFont(new Font("msyh", Font.BOLD, 30));
	   	   draw.text(80, 770,"Wave: "+waveText+"/3" );
	   	   
		   for(int i = 0; i<spawnNumber; i++){			//for loop for controlling player collision 
				if(powerUpVel&&(!alreadySpawnedVel)) {		//if statements for drawing power ups to the screen
				    draw.setFont(new Font("msyh", Font.BOLD, 10));
				    powerUpVelocity= new PowerUps(velPowerUpX,velPowerUpY,powerUpsSize,powerUpsSize,Draw.CYAN);
				    powerUpVelocity.draw();
					draw.setPenColor(Draw.BLACK);
					draw.text(velPowerUpX, velPowerUpY,"increase speed!!" );
				}
				if(powerUpKillEnemy&&(!alreadySpawnedKill)) {
				    draw.setFont(new Font("msyh", Font.BOLD, 10));
				    powerUpEnemyKiller= new PowerUps(killPowerUpX,killPowerUpY,powerUpsSize,powerUpsSize,Draw.MAGENTA);
				    powerUpEnemyKiller.draw();
					draw.setPenColor(Draw.BLACK);
					draw.text(killPowerUpX, killPowerUpY,"kill 3 enemies!" );
				}
				for(Enemy e: enemyArr) {	//for each for controlling the collision with the player
					if(e.isIntersecting(player)&&(!justStarted)) {
						player.shrink();
						e.xCoord=-900; 
						player.tookDamage++;	
						}
					}
										
				for(Food f: foodArr) {	
					if(f.isIntersecting(player)&&(!justStarted)) {
						player.grow();
						f.xCoord=-900; 
						player.ateFood++;
						foodEatenPerWave++;
						int chanceOfDroppingVel = rgen.nextInt(3);
						int chanceOfDroppingKiller = rgen.nextInt(5);
							if(chanceOfDroppingVel==2) {	//just making it 33% chance that a increase velocity powerup will drop
								powerUpVel=true;
								}
							if(chanceOfDroppingKiller==2) {	//just making it 20% chance that a freeze enemy powerup will drop
								powerUpKillEnemy=true;
								}
						  }
					}
			}
		   
			if(powerUpVelocity.isIntersecting(player)&&(!alreadySpawnedVel)) { //if statement for controlling power ups collision with the player
				System.out.println("Increased velocity!");
				powerUpVelocity.increaseSpeed();
				velPowerUpX-=900;
				alreadySpawnedVel=true;
			}
			if(powerUpEnemyKiller.isIntersecting(player)&&(!alreadySpawnedKill)) {
				System.out.println("Increased velocity!");
				powerUpEnemyKiller.killEnemyByClick();
				killPowerUpX-=900;
				alreadySpawnedKill=true;
			}
			player.draw();
			player.setRandomColor();		//drawing the player square and making it look....cooler?
			if (draw.isKeyPressed(KeyEvent.VK_UP)&&player.playerYPos<CANVAS_SIZE-10){
				player.playerYPos+= Player.Velocity;
				justStarted=false;
			  }
			
			else if (draw.isKeyPressed(KeyEvent.VK_DOWN)&&player.playerYPos>10){
				player.playerYPos -= Player.Velocity;
			    justStarted=false;
			
			  }
			else if (draw.isKeyPressed(KeyEvent.VK_LEFT)&&player.playerXPos>10){
			    player.playerXPos -= Player.Velocity;
			    justStarted=false;
			
			  }
			else if (draw.isKeyPressed(KeyEvent.VK_RIGHT)&&player.playerXPos<CANVAS_SIZE-10){
			    player.playerXPos += Player.Velocity;
			    justStarted=false;
			  }
			
			draw.show();  
			draw.pause(2);
			if(player.dead()) {			//if the player radius becomes less than 0, they die... they loose radius when they touch enemies
			      lost=true;
			      gamePlayState=false;	//telling CheckGameState() to go to the lost method
			      draw.clear();
			      Active=false;
			  }
			   if(foodEatenPerWave>=spawnNumber) {
					   if(wave==3) {  //if the player collects all the food and is the last wave they win
					       won=true;
					       gamePlayState=false;
					       draw.clear();
					       Active=false;   
					   }
					   wave++;	//if the player collects all the food go to the next wave
					   waveManagment(wave);   
				  }
       		}
	}
	
	public void Lost() {
		draw.setPenColor(Draw.BLACK);
		double mouseY= draw.mouseY();
		draw.line(0, 220, 800, 220);
		draw.line(0, 470, 800, 470);
		draw.line(0, 700, 800, 700);
	    draw.setFont(new Font("msyh", Font.BOLD, 70));
		draw.text(400, 750,"You Lost!" );
	    draw.setFont(new Font("msyh", Font.BOLD, 40));
		draw.text(400, 570,"Play again (r)" );
		draw.text(400, 350,"Print a performance summary (p)" );
		draw.text(400, 100,"Close (q)" );
		
		if(draw.isMousePressed()) {
			if(mouseY<700 && mouseY>470) {
				ResetStats();	//same logic of mainmenu, except i reset all needed variables before going to the GamePlayState()
				callOnce=true;	//reseting this so GamePlayState() only gets called once again.
				lost=false;
				Active=true;
				numberOfAttempts++;	//just saves how many tries the player has for the summary print.
				wave=1;
				foodEatenPerWave=0;
				gamePlayState=true;
			}
			else if(mouseY<465 && mouseY>227) {
				PrintSummary();		//upon hitting the print performance summary, show that it was printed and call the print method
				draw.text(400, 270,"Done!!!" );
			}
			else if(mouseY<215 && mouseY>5) {
				System.exit(0);		//bye?
			}
		}
		draw.show();
		draw.pause(15);
  }
	public void Won() {
		  draw.setPenColor(Draw.BLACK);
			double mouseY= draw.mouseY();
			draw.line(0, 220, 800, 220);
			draw.line(0, 470, 800, 470);
			draw.line(0, 700, 800, 700);
		    draw.setFont(new Font("msyh", Font.BOLD, 70));
			draw.text(400, 750,"You won!!!!" );
		    draw.setFont(new Font("msyh", Font.BOLD, 40));
			draw.text(400, 570,"Play again (r)" );
			draw.text(400, 350,"Print a performance summary (p)" );
			draw.text(400, 100,"Close (q)" );	//same as the lost state except it shows that you won

			if(draw.isMousePressed()) {
				if(mouseY<700 && mouseY>470) {
					ResetStats();
					callOnce=true;
					lost=false;
					Active=true;
					numberOfAttempts++;
					foodEatenPerWave=0;
					gamePlayState=true;
				}
				else if(mouseY<465 && mouseY>227) {
					PrintSummary();
					draw.text(400, 270,"Done!!!" );
				}
				else if(mouseY<215 && mouseY>5) {
					System.exit(0);
				}
			}
		   draw.show();
		   draw.pause(10);
	}
	public void ResetStats() {  //reset important variables before we start the game again
		player.playerXPos=CANVAS_SIZE/2;
		player.playerYPos=CANVAS_SIZE/2;
		Player.Velocity=3;
    	player.radius=20;
    	player.tookDamage=0;
    	player.ateFood=0;
    	powerUpKillAmount=0;
    	powerUpVel=false;
    	powerUpKillEnemy=false;
    	justStarted=true;
    	alreadySpawnedVel=false;
    	alreadySpawnedKill=false;
    	foodEatenPerWave=0;
		wave=1;
    	foodArr.clear();
    	enemyArr.clear();
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

		public void drawArr() {
			for(Food f: foodArr) {	//for each loop that draws the arrays
				f.draw();	
			}
			for(Enemy e: enemyArr) {	
				e.draw();	
				e.move(); //making the enemy move after being drawn
			}
		}
		private void waveManagment(int wave) {
		    Random rgen = new Random();			   
		    foodEatenPerWave=0;
		    player.damageAmount+=3;		//player takes 3+ damage upon collision with enemies, making waves little harder as you go through them
			if(wave==2) {		//drawing each wave
			   for(int i = 0; i<spawnNumber; i++)
			   {
				   int xEnemy = rgen.nextInt(CANVAS_SIZE);
				   int yEnemy = rgen.nextInt(CANVAS_SIZE);
				   int xFood = rgen.nextInt(CANVAS_SIZE);
				   int yFood = rgen.nextInt(CANVAS_SIZE);
				   int enemyHeight = rgen.nextInt(10)+50;
				   int enemyWidth = rgen.nextInt(10)+50;
				   int foodSize = rgen.nextInt(10)+10;
				   int xVel = rgen.nextInt(2)+3;
				   int yVel = rgen.nextInt(1)+3;
				   enemyArr.set(i,new Enemy(xEnemy,yEnemy,enemyHeight,enemyWidth,xVel,yVel));
				   foodArr.add(new Food(xFood, yFood, foodSize, foodSize));
	
			   }				
			}
		    else if(wave==3) {
			   for(int i = 0; i<spawnNumber; i++)
			   {
				   int xEnemy = rgen.nextInt(CANVAS_SIZE);
				   int yEnemy = rgen.nextInt(CANVAS_SIZE);
				   int xFood = rgen.nextInt(CANVAS_SIZE);
				   int yFood = rgen.nextInt(CANVAS_SIZE);
				   int enemyHeight = rgen.nextInt(10)+50;
				   int enemyWidth = rgen.nextInt(10)+50;
				   int foodSize = rgen.nextInt(10)+10;
				   int xVel = rgen.nextInt(2)+3;
				   int yVel = rgen.nextInt(1)+3;
				   enemyArr.set(i,new Enemy(xEnemy,yEnemy,enemyHeight,enemyWidth,xVel,yVel));
				   foodArr.add(new Food(xFood, yFood, foodSize, foodSize));
			   }		
		    }

		}
		@Override
		public void mousePressed(double x, double y) {
			// TODO Auto-generated method stub
			
			if(alreadySpawnedKill&&powerUpKillAmount<=2) {
				for(int i=0; i<=enemyArr.size()-1;i++) { //for loop that deletes the clicked enemies 3 times only
					if(enemyArr.get(i).clickedAtEnemy(draw.mouseX(),draw.mouseY())) { 
						enemyArr.get(i).xCoord=-900;
						powerUpKillAmount++;
					}
				}	
			}
			
		}

		@Override
		public void mouseDragged(double x, double y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(double x, double y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(double x, double y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(char c) {
			// TODO Auto-generated method stub
			if(!gamePlayState) {
				if(c=='q') {	//quit program when q is clicked, call reset function when r is clicked,p for printing summary 
					System.exit(0);		
					}
				else if(c=='p') {
					PrintSummary();
					draw.text(400, 270,"Done!!!" );
				}
				else if(c=='r') {
					ResetStats();
					callOnce=true;
					lost=false;
					Active=true;
					numberOfAttempts++;
					gamePlayState=true;
				}
			}
	
		}

		@Override
		public void keyPressed(int keycode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(int keycode) {
			// TODO Auto-generated method stub
			
		}

	}
	


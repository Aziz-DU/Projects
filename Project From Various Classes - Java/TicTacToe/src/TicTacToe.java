import edu.princeton.cs.introcs.StdDraw;

public class TicTacToe {
	enum Cell {EMPTY, X, O};
	Cell[][] gameBoard= new Cell[3][3];
	boolean xTurn = true;
	int factorX = 0;
	int factorY = 0;
	String turnMessage = "it's currently  X 's turn";
	int cellPosX=0;
	int cellPosY=0;
	
	public TicTacToe(){
		StdDraw.setCanvasSize(600, 650);
		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 650);
		
		
		for (int row = 0; row < gameBoard.length; row++) { // making all array elements empty
			for (int col = 0; col < gameBoard[row].length; col++) {
		    	  gameBoard[row][col] = Cell.EMPTY;
		      }
		   }
	}
	public void draw() {
		StdDraw.text(300, 625,turnMessage );
		StdDraw.line(0, 400, 600, 400);
		StdDraw.line(0, 200, 600, 200);
		StdDraw.line(200, 0, 200, 600);
		StdDraw.line(400, 0, 400, 600);
		StdDraw.line(0, 600, 600, 600);
	
		    	  
	    if (gameBoard[cellPosX][cellPosY] == Cell.X && xTurn) { //if a cell was assigned to X and it was X's turn
			StdDraw.line(250+factorX, 350+factorY, 350+factorX, 250+factorY);
			StdDraw.line(250+factorX, 250+factorY, 350+factorX, 350+factorY);
			
			if(!gameWon()&&!allFilled()) { // if the game has not won yet and and spaces weren't filled change the top message to O's turn
				turnMessage= "it's currently  O 's turn";
			    StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledRectangle( 320,  625,400,20);
			    StdDraw.setPenColor(StdDraw.BLACK);
				xTurn=false;
				 }
			else {
				gameActive=false;   //stops the loop when the game is over  
			}
						
			}
	    else if (gameBoard[cellPosX][cellPosY] == Cell.O && !xTurn) {//same goes for O from what written above
			StdDraw.circle(300+factorX,300+factorY,50);
			
			if(!gameWon()&&!allFilled()) {
				turnMessage = "it's currently  X 's turn";
			    StdDraw.setPenColor(StdDraw.WHITE);
		    	StdDraw.filledRectangle( 320,  625,400,20);
				StdDraw.setPenColor(StdDraw.BLACK);
				xTurn=true;
				}
			else {
				gameActive=false;
			}

		   }
	
	}
	boolean gameActive=true;

	public void playGame() {
		//boolean gameActive=true;
		while(gameActive) {

			double mouseX= StdDraw.mouseX();
			double mouseY= StdDraw.mouseY();
			double xPos= mouseX / 200;        //divide mousex,y by 200 because canvas size is 600,650("600" for y because 50 is just for the message up top)
			double yPos= mouseY / 200;		
	
			
		    StdDraw.enableDoubleBuffering();		   	  

					draw();
					if(StdDraw.isMousePressed() && !gameWon()) {
						if(xPos<1) {     //if result is between 0-1 i assign it to cell [0], 1-2=[1], 2-3=[2]. 
							cellPosX=0;
							factorX=-200; // factorX is for moving the X or O Draw to their position depending on which cell you clicked on. 
						}
						else if(xPos>1 && xPos<2) {
							cellPosX=1;
							factorX=0;
						}
						else if(xPos>2) {
							cellPosX=2;
							factorX=200;
						}
						if(yPos<1) {
							cellPosY=0;
							factorY=-200;
						}
						else if(yPos>1 && yPos<2) {
							cellPosY=1;
							factorY=0;

						}
						else if(yPos>2) {
							cellPosY=2;
							factorY=200;

						}
						if(gameBoard[cellPosX][cellPosY] == Cell.EMPTY&&!allFilled() ) {// if the cell clicked on is empty and all the spaces aren't filled
						if(xTurn) {
							gameBoard[cellPosX][cellPosY]=Cell.X;

						}
						else {
							gameBoard[cellPosX][cellPosY]=Cell.O;

						}

					}
				}
					StdDraw.show();
					gameWon();
		}
			
	}

	public boolean gameWon() { //if any diagonal or ver/hor lines have the same value, they win
		if(gameBoard[0][0] == Cell.X && gameBoard[0][1] == Cell.X && gameBoard[0][2] == Cell.X || 
			gameBoard[1][0] == Cell.X && gameBoard[1][1] == Cell.X && gameBoard[1][2] == Cell.X ||          
			gameBoard[2][0] == Cell.X && gameBoard[2][1] == Cell.X && gameBoard[2][2] == Cell.X || 
			gameBoard[0][0] == Cell.X && gameBoard[1][0] == Cell.X && gameBoard[2][0] == Cell.X ||
			gameBoard[0][1] == Cell.X && gameBoard[1][1] == Cell.X && gameBoard[2][1] == Cell.X || 
			gameBoard[0][2] == Cell.X && gameBoard[1][2] == Cell.X && gameBoard[2][2] == Cell.X || 
			gameBoard[0][0] == Cell.X && gameBoard[1][1] == Cell.X && gameBoard[2][2] == Cell.X ||       
			gameBoard[2][0] == Cell.X && gameBoard[1][1] == Cell.X && gameBoard[0][2] == Cell.X) {

	   	    StdDraw.setPenColor(StdDraw.WHITE); //clear top message manually because im not using stdclear.
			StdDraw.filledRectangle( 320,  625,400,20);
		    StdDraw.setPenColor(StdDraw.BLACK);
			String turnMessage = "X won the game!";
			StdDraw.text(300, 625,turnMessage );
			return true;

		}
		else if(gameBoard[0][0] == Cell.O && gameBoard[0][1] == Cell.O && gameBoard[0][2] == Cell.O || 
			gameBoard[1][0] == Cell.O && gameBoard[1][1] == Cell.O && gameBoard[1][2] == Cell.O ||    
			gameBoard[2][0] == Cell.O && gameBoard[2][1] == Cell.O && gameBoard[2][2] == Cell.O || 
			gameBoard[0][0] == Cell.O && gameBoard[1][0] == Cell.O && gameBoard[2][0] == Cell.O || 
			gameBoard[0][1] == Cell.O && gameBoard[1][1] == Cell.O && gameBoard[2][1] == Cell.O ||
			gameBoard[0][2] == Cell.O && gameBoard[1][2] == Cell.O && gameBoard[2][2] == Cell.O || 
			gameBoard[0][0] == Cell.O && gameBoard[1][1] == Cell.O && gameBoard[2][2] == Cell.O ||         
			gameBoard[2][0] == Cell.O && gameBoard[1][1] == Cell.O && gameBoard[0][2] == Cell.O) {
			
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle( 320,  625,400,20);
			StdDraw.setPenColor(StdDraw.BLACK);
			String turnMessage = "O won the game!";
			StdDraw.text(300, 625,turnMessage );
			return true;

			}
		return false;

	}

	public boolean allFilled() {//if none of the cells are empty, update message to show that it's a tie
		for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[x].length; y++) {
				if (gameBoard[x][y] == Cell.EMPTY) {
					return false;					 
				}
			}
		}
	StdDraw.setPenColor(StdDraw.WHITE);
	StdDraw.filledRectangle( 320,  625,400,20);
	StdDraw.setPenColor(StdDraw.BLACK);
	String turnMessage = "It's a tie!";
	StdDraw.text(300, 625,turnMessage );
	return true;
	}
}


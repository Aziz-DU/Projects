public class Driver 
{     
   public static void main(String[] args)
   {

   boolean active = true;
   GameState gamestate=new GameState();  
   

   while(active) {
	   gamestate.CheckGameState();	//the only method we need, it controls the whole game
	//   gamestate.GamePlayState();
   		  }

  }

}




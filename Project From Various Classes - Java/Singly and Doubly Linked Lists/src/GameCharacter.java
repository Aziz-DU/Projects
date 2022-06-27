
public class GameCharacter {
	private String name;
public GameCharacter(String n) {
	name=n;
}
public String toString() {
	return name;	
}
public boolean equals(Object obj)
{
   if (obj == null)
   {
      return false;
   }

   if (this.getClass() != obj.getClass())
   {
      return false;
   }
   GameCharacter other = (GameCharacter) obj;
   if(this.name!=other.name) {
	  return false;
   }
   return true;
}
}

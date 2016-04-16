public class Main 
{
	public static void main(String[] args)
	{
		GameFactory game = new GameFactory();
		
		Game BigTwo = game.startBigTwo();

		BigTwo.execute();
	}
}
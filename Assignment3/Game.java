import java.util.*;

public abstract class Game
{
    private ArrayList<Card> cards;
    private Player player;
	
    public abstract void shuffle();
    public abstract  void playGame();
    public abstract  void endGame();

    //template method	
    public final void execute()
    {
        shuffle();
        playGame();
        endGame();
    }
}

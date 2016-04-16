import java.util.*;
public class GameFactory
{
	public Game startGoFish()
	{
		return new GoFish();
	}
	public Game startBigTwo()
	{
		return new BigTwo();
	}
}

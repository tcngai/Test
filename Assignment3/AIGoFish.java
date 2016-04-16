import java.util.*;
//AI for GoFish game that will add additional functionality
public class AIGoFish extends AIDecorator
{
	private ArrayList<Card> cards;
	
	public AIGoFish(Players decoratedAI)
	{
		super(decoratedAI);
	}
	public void removeCard(Card card)
	{
		decoratedAI.removeCard(card);
		System.out.println("in gofish ai");
		gofishremove();
	}
	public void gofishremove()
	{
		System.out.println("gofish remove card for go fish game");
	}
	public boolean addCard(Card card)
	{
		System.out.println("in addcard gofish");
        	//return cards.add(card);
		return decoratedAI.addCard(card);
    	}
	public void showAll()
	{
		decoratedAI.showAll();
	}
	public boolean contains(Card card)
	{
		return cards.contains(card);
	}
    	public boolean cut()
	{
		System.out.println("in cut for gofish");
        	// do nothing 
		return true;
    	}
	
}

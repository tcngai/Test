import java.util.*;
//AI for crazy 8 game that will add additional functionality
public class AIcrazy8 extends AIDecorator
{
	private ArrayList<Card> cards;
	
	public AIcrazy8(Players decoratedAI)
	{
		super(decoratedAI);
	}
	public void removeCard(Card card)
	{
		decoratedAI.removeCard(card);
		System.out.println("in Crazy8 ai");
		crazy8remove();
	}
	public void crazy8remove()
	{
		System.out.println("Crazy8 remove card for Crazy8 game");
	}
	public boolean addCard(Card card)
	{
		System.out.println("in add card Crazy8");
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
		System.out.println("in cut for Crazy8");
        	// do nothing 
		return true;
    	}
	
}

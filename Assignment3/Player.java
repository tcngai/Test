import java.util.*;
public class Player implements Players
{
    private ArrayList<Card> cards;
    
    public Player()
    {
        cards = new ArrayList<Card>();
    }
    public void removeCard(Card card)
    {
        cards.remove(card);
    }
    public boolean addCard(Card card)
    {
        return cards.add(card);
    }
    public boolean contains(Card card)
    {
		return cards.contains(card);
    }
    public void showAll()
    {
	for (int i=0;i<cards.size();i++)
	{
		if (cards.get(i).getSuit()==1)
			System.out.print(cards.get(i).getRank() + " Diamond  ");
		else if (cards.get(i).getSuit()==2)
			System.out.print(cards.get(i).getRank() + " Club  ");
		else if (cards.get(i).getSuit()==3)
			System.out.print(cards.get(i).getRank() + " Heart  ");
		else if (cards.get(i).getSuit()==4)
			System.out.print(cards.get(i).getRank() + " Spade  ");
	}
	System.out.println();
    }
	public int size()
	{
		return cards.size();
	}
	public Card get(int i)          // get card by index
	{
		return cards.get(i);
	}
    public boolean cut()
    {
        // do nothing 
	return true;
    }
}

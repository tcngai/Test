import java.util.*;
//used for BigTwo game
public class PokerHand
{
	public boolean straight(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		ArrayList<Integer> cards = new ArrayList<Integer>();
		cards.add(card1.getRank());
		cards.add(card2.getRank());
		cards.add(card3.getRank());
		cards.add(card4.getRank());
		cards.add(card5.getRank());
		Collections.sort(cards);
		if(cards.get(0)+1 == cards.get(1) && cards.get(1)+1 == cards.get(2) && cards.get(2)+1 == cards.get(3) && cards.get(3)+1 == cards.get(4) ) // general straight
			return true;
		else if(cards.get(0) == 1 && cards.get(1) == 10 && cards.get(2) == 11 && cards.get(3) == 12 && cards.get(4) == 13) //A10JQK
			return true ;
		else 
			return false;
	}
	public boolean flush(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		if(card1.getSuit() ==  card2.getSuit()  && card1.getSuit() ==  card3.getSuit() && card1.getSuit() ==  card4.getSuit()  && card1.getSuit() ==  card5.getSuit())
			return true;
		else 
			return false;
	}
	public boolean straightFlush(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		if (straight(card1,card2,card3,card4,card5) && flush(card1,card2,card3,card4,card5))
			return true;
		else 
			return false;
	}
	public boolean threeOfAKind(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		ArrayList<Integer> cards = new ArrayList<Integer>();
		cards.add(card1.getRank());
		cards.add(card2.getRank());
		cards.add(card3.getRank());
		cards.add(card4.getRank());
		cards.add(card5.getRank());
		Collections.sort(cards);
		boolean same3=false , same2=false;
		int counter = 1, counter2=0;
		int temp2 = 0,temp = 0;
		temp=cards.get(0);
		for(int i=1; i<5; i++)
		{
			if (cards.get(i) == temp)
				counter++;
			else 
				temp2 = cards.get(i);
			if (cards.get(i)==temp2)
				counter2++;
		}
		if ((counter==3 && counter2==2) || (counter==2 && counter2==3))
			return true;
		else 
			return false;
			
	}
	public boolean fourOfAKind(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		ArrayList<Integer> cards = new ArrayList<Integer>();
		cards.add(card1.getRank());
		cards.add(card2.getRank());
		cards.add(card3.getRank());
		cards.add(card4.getRank());
		cards.add(card5.getRank());
		Collections.sort(cards);
		boolean same3=false , same2=false;
		int counter = 1, counter2=0;
		int temp2 = 0,temp = 0;
		temp=cards.get(0);
		for(int i=1; i<5; i++)
		{
			if (cards.get(i) == temp)
				counter++;
			else 
				temp2 = cards.get(i);
			if (cards.get(i)==temp2)
				counter2++;
		}
		if ((counter==4 && counter2==1) || (counter2==4 && counter==1))
			return true;
		else 
			return false;
			
	}
	public boolean triplet(Card card1, Card card2, Card card3)
	{
		if  (card1.getRank() == card2.getRank() && card1.getRank() == card3.getRank())
			return true;
		else 
			return false;
	}
	public boolean pair(Card card1, Card card2)
	{
		if (card1.getRank() == card2.getRank())
			return true;
		else 
			return false;
	}

}

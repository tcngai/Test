import java.util.*;
//AI for BigTwo game that will add additional functionality
public class AIBigTwo extends AIDecorator
{
	private ArrayList<Card> cards;
	public AIBigTwo(Players decoratedAI)
	{
		super(decoratedAI);
	}
	public void removeCard(Card card)
	{
		decoratedAI.removeCard(card);
		//System.out.println("in bigtwo ai");
		//bigtworemove();
	}
	//public void bigtworemove()
	//{
	//	System.out.println("bigtwo remove card for bigtwo game");
	//}
	public boolean addCard(Card card)
	{
		//System.out.println("in addcard bigtwo");
		//return cards.add(card);
		return decoratedAI.addCard(card);
    	}
	public boolean contains(Card card)
	{
		return decoratedAI.contains(card);
	}
	public int size()
	{
		return decoratedAI.size();
	}
    public boolean cut()
	{
        	// do nothing 
		return true;
    }
	public void showAll()
	{
		decoratedAI.showAll();
	}
	public String translateSuit(int i){
		if(i==1)
			return "Diamond";
		else if(i==2)
			return "Club";
		else if(i==3)
			return "Heart";
		else if(i==4)
			return "Spade";
		else
			return "ERROR";
	}
	// Function that given the big two type and card 
	// remove the card ai choose and return that card/ if pass return a invalid card with -1 suit and rank
	public Card Move(String type,Card card){
		if(type=="single"){
			boolean found=false;
			for(int i=0; i<decoratedAI.size();i++){
	    	    if(larger(decoratedAI.get(i),card)){                                      // looking for a card that is larger
	    	        System.out.println(translateSuit(decoratedAI.get(i).getSuit())+" "+decoratedAI.get(i).getRank());
	    	        Card returnCard = new Card(decoratedAI.get(i).getSuit(),decoratedAI.get(i).getRank());
					removeCard(decoratedAI.get(i));                             						// remove player card by index 
					return returnCard;
	    	    }
	    	}
		}
		else if(type=="free"){
			Card returnCard = decoratedAI.get(0);
		    System.out.println(translateSuit(returnCard.getSuit())+" "+returnCard.getRank());
			removeCard(decoratedAI.get(0));
			return returnCard;
		}
		else if(type=="first"){
			Card Diamond3 = new Card(1,3);
			if (contains(Diamond3)){
				System.out.println(translateSuit(Diamond3.getSuit())+" "+Diamond3.getRank());
				removeCard(Diamond3);
				return Diamond3;
			}
		}
		System.out.println("PASS");
		return new Card(0,0);
	}
	// Return true if card 1 is larger than card 2 
	public boolean larger(Card card1,Card card2){
		if(card1.getRank()==card2.getRank()){
			if(card1.getSuit()>card2.getSuit())
				return true;
			else 
				return false;
		}else if (card1.getRank()<=2 && card2.getRank()<=2){ // if both card is 1 or 2
			if(card1.getRank()>card2.getRank())
				return true;
			else 
				return false;
		}else if (card1.getRank()<=2 && card2.getRank()>2){
			return true;
		}else if (card1.getRank()>2 && card2.getRank()<=2){
			return false;
		}else
			return false;
	}
}

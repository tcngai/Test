import java.util.*;
public class BigTwo extends Game
{
    public ArrayList<Card> cards;
    public Player player;
	private int winner;
	private int turn;
	private int pass;
	private String currentType; // first,singe,pair,triplet,pokerhand
	private int pokerhandType; // 1 straight, 2 flush, 3 full house, 4 of a kind, 5 straightish 
	private Card currentCard;	
	private ArrayList<AIBigTwo> AIs;
	private PokerHand Rule;
	
	public BigTwo()
	{
		Rule=new PokerHand();
		pass=0;
		currentType="first";
		pokerhandType=0;
		winner=-1; // no winner
		turn=0;    // set turn   0=AI, player=3
		currentCard = new Card(0,0);
	}
	public void shuffle()
	{
		
		cards = new ArrayList<Card>();
		// Shuffle 
		for(int i=1; i<=13; i++)
		{
			for(int y=1; y<=4; y++)
			{
				cards.add(new Card(y,i));
			}
		}
		Collections.shuffle(cards);
		// Assign card
		AIs = new ArrayList<AIBigTwo>();
		for(int i=0; i<3; i++)    // 3 AI
			AIs.add(new AIBigTwo(new Player()));
		player = new Player();
		
		while (player.size()<13)
		{
			player.addCard(cards.get(0));
			cards.remove(0);
			for(int i=0; i<AIs.size(); i++)
			{
				if (cards.size()!=0)
				{
					AIs.get(i).addCard(cards.get(0));
					cards.remove(0);
				}
			}
		}
		
		player.showAll();
	}
	public void playGame()
	{
		Scanner in = new Scanner(System.in);
		String input="";
		while(winner==-1)
		{
			if (input.matches("exit"))
				break;
			if (turn == 3){		//player turn
				player.showAll();
				boolean valid=false;
				while(!valid){
	        		System.out.println("Enter Card you pick: ");
	        		input = in.nextLine();
					if (input.matches("exit"))
	        		    break;
					System.out.println("=========================================================================================");
					if (input.matches("exit"))
	        		    break;
	        		valid=Validate(input);
				}
				turn=-1;
			}else{ 
				System.out.print("AI "+(turn+1)+" :");
				Card AIpick = AIs.get(turn).Move(currentType,currentCard); // AI turns
				if(AIpick.getSuit()==0 && AIpick.getRank()==0)
					pass=pass+1;
				else{
					currentCard=AIpick;
					currentType="single";
					pass=0;
				}
			}
			
			/*for(int i=0; i<AIs.size(); i++)
			{
				AIs.get(i).showAll();

			}*/
			// Pass Count
			if(pass==3 && currentType!="first"){
				currentType="free";
				pokerhandType=0;
				currentCard= new Card(0,0);
			}
			// Next turn
			turn++;
			// Find Winner
			for(int i=0; i<AIs.size(); i++)
			{
				if (AIs.get(i).size()==0)
					winner=i+1;
			}
			if(player.size()==0)
				winner=4;
		}
	}
	public void endGame()
	{
		if(winner==4)
			System.out.println("You Won");
		else
			System.out.println("The Winner is Player:"+winner);
	}
	// Return true if card 1 is larger than card 2 
	public boolean larger(Card card1,Card card2){
		if(card2.getRank()<=0)
			return true;
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
		}else if (card1.getRank()>2 && card2.getRank()>2){
			if(card1.getRank()>card2.getRank())
				return true;
			else 
				return false;
		}else
			return false;
	}
	// return the largest card in the 5 card
	public Card Largest(Card card1, Card card2, Card card3, Card card4, Card card5){
		if(larger(card1,card2) && larger(card1,card3) && larger(card1,card4) && larger(card1,card5))
			return card1;
		else if (larger(card2,card1) && larger(card2,card3) && larger(card2,card4) && larger(card2,card5))
			return card2;
		else if (larger(card3,card1) && larger(card3,card2) && larger(card3,card4) && larger(card3,card5))
			return card3;
		else if (larger(card4,card1) && larger(card4,card2) && larger(card4,card3) && larger(card4,card5))
			return card4;
		else 
			return card5;
	}
	public Card Kind(Card card1, Card card2, Card card3, Card card4, Card card5)
	{
		ArrayList<Integer> cards = new ArrayList<Integer>();
		cards.add(card1.getRank());
		cards.add(card2.getRank());
		cards.add(card3.getRank());
		cards.add(card4.getRank());
		cards.add(card5.getRank());
		Collections.sort(cards);
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
		if ((counter > counter2))
			return new Card(4,temp);
		else
			return new Card(4,temp2);			
	}
	public boolean Validate(String input){
	    if (input.toLowerCase().matches("pass")){
	        pass++;
	        return true;
	    }
		input=input+' ';
	    ArrayList<Card> selectedCards= new ArrayList<Card>();
        //  Putting Card to an array of string
	    String suitString="";
		String rankString="";
		while(input.indexOf(' ')>0){
			Card temp;
			suitString = input.substring(0,1);
			rankString = input.substring(1,input.indexOf(' '));
			suitString=suitString.toLowerCase();
			if(suitString.compareTo("s")==0)
				temp=new Card(4,Integer.parseInt(rankString));
			else if (suitString.compareTo("h")==0)
				temp=new Card(3,Integer.parseInt(rankString));
			else if (suitString.compareTo("c")==0)
				temp=new Card(2,Integer.parseInt(rankString));
			else if (suitString.compareTo("d")==0)
				temp=new Card(1,Integer.parseInt(rankString));
			else{
				System.out.println("Wrong Input");
				return false;
			}	
			selectedCards.add(temp);
			input=input.substring(input.indexOf(' ')+1);
		}
	    
		// Player own those cards or not
	    for(int i=0; i<selectedCards.size();i++){
			if(!player.contains(selectedCards.get(i))){
				System.out.println("You don't have the following card "+selectedCards.get(i).getRank());
				return false;
			}
	    }
		/*for(int i=0; i<selectedCards.size();i++){
			for(int j=i+1; j<selectedCards.size();j++){
				if(selectedCards.get(i).matches(selectedCards.get(j))){
					System.out.println("You cannot have same card in one deck of card "+selectedCards.get(i));
					return false;
				}
			}
	    }*/
		if((!selectedCards.contains(new Card(1,3))) && currentType.matches("first")){
			System.out.println("First turn must use Diamond 3(D3)");
			return false;
		}
		if((selectedCards.contains(new Card(1,3))) && currentType.matches("first"))
			currentType="free";   													// Type is free as long as they contain diamond 3
	    // ====================================================  Validate Logic in big 2  ==============================================
		if(selectedCards.size()==5){				// Pokerhand
			if((!currentType.matches("pokerhand")) &&  (!currentType.matches("free"))){
				System.out.println("You cannot use pokerhand current type is "+currentType);
				return false;
			}
			if((Rule.straightFlush(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)))){
				if (pokerhandType<5 ||
					(pokerhandType==5 && larger(Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)),currentCard))
					){
				    currentType="pokerhand";
					pokerhandType=5;
				    currentCard=Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4));
				    pass=0;
				    for(int i=0; i<5; i++)
				        player.removeCard(selectedCards.get(i));
				    return true;
				}
				else{
				    System.out.println("Your Pokerhand is smaller than the last one");
			   		return false;
				}
			}
			else if(Rule.threeOfAKind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4))){
			    if (pokerhandType<3 ||
					( pokerhandType==3 && larger(Kind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)),currentCard))
					){
				    currentType="pokerhand";
					pokerhandType=3;
				    currentCard=Kind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4));    // Get the 3 of a kind card
				    pass=0;
				    for(int i=0; i<5; i++)
				        player.removeCard(selectedCards.get(i));
			        return true;
				}
				else{
				    System.out.println("Your Pokerhand is smaller than the last one");
				    return false;
				}
			}
			else if(Rule.fourOfAKind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4))){
			   if (pokerhandType<4 ||
					( pokerhandType==4 && larger(Kind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)),currentCard))
					){
				    currentType="pokerhand";
					pokerhandType=4;
				    currentCard=Kind(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4));
				    pass=0;
				     for(int i=0; i<5; i++)
				        player.removeCard(selectedCards.get(i));
				    return true;
				}
				else{
				    System.out.println("Your Pokerhand is smaller than the last one");
				    return false;
				}
			}
			else if(Rule.straight(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4))){
				  if ( pokerhandType<1 ||
				   (pokerhandType==1 && larger(Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)),currentCard))
					){
				    currentType="pokerhand";
					pokerhandType=1;
				    currentCard=Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4));
				    pass=0;
				    for(int i=0; i<5; i++)
				        player.removeCard(selectedCards.get(i));
				    return true;
				}
				else{
				    System.out.println("Your Pokerhand is smaller than the last one");
				    return false;
				}
			}
			else if(Rule.flush(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4))){
				if ( pokerhandType<2 ||
				   (pokerhandType==2 && larger(Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4)),currentCard))
					){
				    currentType="pokerhand";
					pokerhandType=2;
				    currentCard=Largest(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2),selectedCards.get(3),selectedCards.get(4));
				    pass=0;
				    for(int i=0; i<5; i++)
				        player.removeCard(selectedCards.get(i));
				    return true;
				}
				else{
				    System.out.println("Your Pokerhand is smaller than the last one");
				    return false;
				}
			}
			else{
			    System.out.println("Unknow pokerhand"+selectedCards.get(0)+", "+selectedCards.get(1)+", "+selectedCards.get(2)+", "+selectedCards.get(3)+", "+selectedCards.get(4));
			    return false;
		    }
		}
		else if (selectedCards.size()==3){			// Triplet
			if((!currentType.matches("triplet")) &&  (!currentType.matches("free"))){
				System.out.println("You cannot use triplet current type is "+currentType);
				return false;
			}
		    if(Rule.triplet(selectedCards.get(0),selectedCards.get(1),selectedCards.get(2))){
		        if (larger(selectedCards.get(0),currentCard)){
    		        currentType="triplet";
				    currentCard=selectedCards.get(0);
				    pass=0;
    		        for(int i=0; i<3; i++)
				        player.removeCard(selectedCards.get(i));           
    				return true;
		        }
		        else{
		            System.out.println("Your triplet is smaller than the last one");
		            return false;
		        }
	        }
		    else{
		        System.out.println(selectedCards.get(0)+", "+selectedCards.get(1)+", "+selectedCards.get(2)+"is not a triplet");
		        return false;
		    }
		}
		else if (selectedCards.size()==2){			// Pair		
			if((!currentType.matches("pair")) &&  (!currentType.matches("free"))){
				System.out.println("You cannot use pair current type is "+currentType);
				return false;
			}
		    if(Rule.pair(selectedCards.get(0),selectedCards.get(1))){
		        if (larger(selectedCards.get(0),currentCard)){
    		        currentType="pair";
				    currentCard=selectedCards.get(0);
				    pass=0;
    		        for (int i=0; i<2; i++)
						player.removeCard(selectedCards.get(i));        
			        return true;
		        }
		        else{
		            System.out.println("Your pair is smaller than the last one");
		            return false;
		        }
	        }
		    else{
		        System.out.println(selectedCards.get(0)+", "+selectedCards.get(1)+"is not a pair");
		        return false;
		    }
		}
		else if (selectedCards.size()==1){			// Single
			if((!currentType.matches("single")) &&  (!currentType.matches("free"))){
				System.out.println("You cannot use single current type is "+currentType);
				return false;
			}
		    if (larger(selectedCards.get(0),currentCard)){
		        currentType="single";
			    currentCard=selectedCards.get(0);
				player.removeCard(selectedCards.get(0));        
			    pass=0;
		        return true;
		    }
		    else{
		        System.out.println("Your card is smaller than the last one");
		        return false;
		    }
        }
		else{
			System.out.println("Your card is not valid in any type in Big Two");
			return false;
		}
    }
}

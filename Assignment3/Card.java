public class Card
{
    private int suit; // 4 = spade 3 = heart 2 = club 1 = diamond
    private int rank;
	
    public Card(int Suit, int Rank)
    {
        suit = Suit;
        rank = Rank;
    }
    public int getRank()
    {
        return rank;
    }   
    public int getSuit()
    {
        return suit;
    }
   /*	equals method used to compare card object
	takes Object as parameter 
	returns either true if the objects are equal or false */
    public boolean equals(Object o)
    {
	if (!(o instanceof Card))
		return false;
	Card card = (Card) o;

	return getSuit() == card.getSuit() && getRank() == card.getRank();
    }
}

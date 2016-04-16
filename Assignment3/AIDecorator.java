//Decorator class for AI
public abstract class AIDecorator implements Players
{
	protected Players decoratedAI;
	
	public AIDecorator(Players decoratedAI)
	{
		this.decoratedAI = decoratedAI;
	}
	public void removeCard(Card card)
	{
		decoratedAI.removeCard(card);
	}
	public boolean addCard(Card card)
	{
		//return true;
		return decoratedAI.addCard(card);
	}
	public boolean contains(Card card)
	{
		//return true;
		return decoratedAI.contains(card);
	}
	public int size()
	{
		return decoratedAI.size();
	}
	public Card get(int i)          // get card by index
	{
		return decoratedAI.get(i);
	}
	public abstract boolean cut();
	
	public abstract void showAll();
	
}

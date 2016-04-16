import java.util.*;
public interface Players
{
	void removeCard(Card card);
	boolean addCard(Card card);
	boolean contains(Card card);
	boolean cut();
	void showAll();
	public int size();
	public Card get(int i);
}
import java.util.*;

public class CharacterNode<T> {
	public Character nodeValue;
	public int count;
	public CharacterNode<T> parent;
	public HashMap<Character, CharacterNode<T>> children;
}


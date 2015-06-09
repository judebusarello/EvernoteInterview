import java.util.*;

public class CharacterNode<T> {
	public int characterValue;
	public int count;
	public CharacterNode<T> parent;
	public HashMap<Integer,CharacterNode<T>> children;
}


import java.util.*;

public class TrieNode {
	public Character nodeValue;
	public int count;
	public TrieNode parent;
	public HashMap<Character, TrieNode> children;
}


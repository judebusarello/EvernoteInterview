import java.util.*;

public class PrefixTree<T> {
	/*
	 *
	 * Below is related to converting the input to a prefix tree representation of the file
	 *
	 */
	private CharacterNode<T> root;
	private CharacterNode<T> currentCharNode;
	public PrefixTree(){
		//The root is used for it's children. it's a starting point.
		//If it ever gets incremented, it's because there are consecutive delimiters.
		root = new CharacterNode<T>();
		root.characterValue = ' ';
		root.count = 0; 
		root.parent = null;
		root.children = new HashMap<Integer,CharacterNode<T>>();
		currentCharNode = root;
	}
	public static class CharacterNode<T> {
		private int characterValue;
		private int count;
		private CharacterNode<T> parent;
		private HashMap<Integer,CharacterNode<T>> children;
	}
	public void ProcessCharacter(int currentChar){
		if(currentChar == -1 || (char)currentChar == ' '){
			//we've reached a delimeter. increment increment the count and start again.
			//System.out.println("end  :" + (char)currentChar);
			currentCharNode.count++;
			currentCharNode = root;
		}
		else if(currentCharNode.children.get(currentChar) != null){
			//the node already exists
			//System.out.println("found:" + (char)currentChar);
			currentCharNode = currentCharNode.children.get(currentChar);
		} 
		else{
			//make a new node
			//System.out.println("new  :" + (char)currentChar);
			CharacterNode<T> newNode = new CharacterNode<T>();
			newNode.characterValue = currentChar;
			newNode.count = 0;
			newNode.parent = currentCharNode;
			newNode.children = new HashMap<Integer,CharacterNode<T>>();
			//use hashmap and add the new node
			currentCharNode.children.put(new Integer(currentChar), newNode);
			//update currentCharNode
			currentCharNode = newNode;
		}
		return;
	}

	/*
	 *
	 * Below deals with converting the prefix tree into a Heap and partial sorting it to find most frequent words.
	 *
	 */
	public String[] ReturnMostFrequent(int OutputSize){
		Comparator<StringNode> comparator = new HeapComparator();
		PriorityQueue<StringNode> minHeap = new PriorityQueue<StringNode>(OutputSize + 1,comparator);
		PopulateHeap("", root, OutputSize, minHeap);
		String[] sortedOutput = new String[OutputSize];
		for(int i = OutputSize - 1; i >= 0; i--){
			sortedOutput[i] = minHeap.poll().word;
		}
		return sortedOutput;
	}
	private PriorityQueue<StringNode> minHeap;
	public class StringNode{
		private String word;
		private int count;
	}
	public class HeapComparator implements Comparator<StringNode>{
		public int compare(StringNode x, StringNode y){
			return x.count - y.count;
		}
	}
	private void PopulateHeap(String currentWord,CharacterNode<T> currentNode, int OutputSize, PriorityQueue<StringNode> minHeap){
		//iterate through our tree and put each word on a priority queue according to the count at each node.
		//do a partial sort using a heap. Add the first k to the heap (output size) then do the old, add 1, remove the largest trick.
		//unordered partial sorting with a heap.
		//Then we just move the heap to an array.
		currentWord = currentWord + (char)currentNode.characterValue;
		if(currentNode.count != 0){
			StringNode newNode = new StringNode();
			newNode.word = currentWord;
			newNode.count = currentNode.count;
			minHeap.add(newNode);
			if(minHeap.size() > OutputSize){
				minHeap.poll();
			}
		}
		//System.out.println(currentWord);
		for(int charValue : currentNode.children.keySet()){
			PopulateHeap(currentWord, currentNode.children.get(charValue), OutputSize, minHeap);
		}
		return;
	}
}

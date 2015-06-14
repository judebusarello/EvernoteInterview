import java.util.*;

public class PrefixTree {

	/* Private variables*/
	private TrieNode root;                //NOTE:The root should always be null
	private TrieNode currentCharNode;     //Iterator variable to save state across multiple invocations of "ProcessCharacter"
	private int totalWordsInTree;

	/*Constructor To Initialize Empty Tree*/
	public PrefixTree(){
		root = new TrieNode();
		root.nodeValue = new Character(' ');
		root.count = 0;
		root.parent = null;
		root.children = new HashMap<Character, TrieNode>();
		currentCharNode = root;
		totalWordsInTree = 0;
	}

	/*Method For Adding a New Character to the Prefix Tree*/
	public void ProcessCharacter(Character currentChar){
		if(Character.isISOControl(currentChar) || Character.isWhitespace(currentChar)){
			//we've reached a delimeter. increment increment the count and start again.
			currentCharNode.count++;
			currentCharNode = root;
		}
		else if(currentCharNode.children.get(currentChar) != null){
			//the node already exists
			currentCharNode = currentCharNode.children.get(currentChar);
		}
		else{
			//make a new node
			TrieNode newNode = new TrieNode();
			newNode.nodeValue = currentChar.charValue();
			newNode.count = 0;
			newNode.parent = currentCharNode;
			newNode.children = new HashMap<Character,TrieNode>();
			//use hashmap and add the new node
			currentCharNode.children.put(currentChar, newNode);
			//update currentCharNode
			currentCharNode = newNode;
			//increment the total
			totalWordsInTree++;
		}
		return;
	}

	/*Method Needed So We Can Use Counting Sort On The Frequency Data*/
	public StringNode[] ReturnArrayOfWords(){
		StringNode[] wordArray = new StringNode[totalWordsInTree];
		FillWordArray("", root, wordArray);
		return wordArray;
	}

	int currentIndex = 0;

	/*Method to traverse(DFS) the tree and fill an array with fully composed words/frequency data*/
	private void FillWordArray(String currentWord,TrieNode currentNode, StringNode[] wordArray){
		currentWord = currentWord + currentNode.nodeValue.charValue();
		for(Character child : currentNode.children.keySet()){
			FillWordArray(currentWord, currentNode.children.get(child), wordArray);
		}
		if(currentNode.count != 0){
			StringNode newNode = new StringNode();
			newNode.word = currentWord;
			newNode.count = currentNode.count;
			wordArray[currentIndex] = newNode;
			currentIndex++;
		}
		return;
	}
}

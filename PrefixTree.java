import java.util.*;

public class PrefixTree<T> {

	/* Private variables*/
	private CharacterNode<T> root;                //NOTE:The root should always be null
	private CharacterNode<T> currentCharNode;     //Iterator variable to save state across multiple invocations of "ProcessCharacter"
	private int              totalWordsInTree;

	/*Constructor To Initialize Empty Tree*/
	public PrefixTree(){
		root = new CharacterNode<T>();
		root.nodeValue = new Character(' ');
		root.count = 0;
		root.parent = null;
		root.children = new HashMap<Character, CharacterNode<T>>();
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
			CharacterNode<T> newNode = new CharacterNode<T>();
			newNode.nodeValue = currentChar.charValue();
			newNode.count = 0;
			newNode.parent = currentCharNode;
			newNode.children = new HashMap<Character,CharacterNode<T>>();
			//use hashmap and add the new node
			currentCharNode.children.put(currentChar, newNode);
			//update currentCharNode
			currentCharNode = newNode;
			//increment the total
			totalWordsInTree++;
		}
		//System.out.println(currentChar.charValue());
		return;
	}

	/*Method Needed So We Can Use Counting Sort On The Frequency Data*/
	public StringNode[] ReturnArrayOfWords(){
		StringNode[] wordArray = new StringNode[totalWordsInTree];
		FillWordArray("", root, wordArray);
		//System.out.println(wordArray);
		//System.out.println(totalWordsInTree);
		return wordArray;
	}

	int currentIndex = 0;

	/*Method to traverse(DFS) the tree and fill an array with fully composed words/frequency data*/
	private void FillWordArray(String currentWord,CharacterNode<T> currentNode, StringNode[] wordArray){
		currentWord = currentWord + currentNode.nodeValue.charValue();
		//for(int charValue : currentNode.children.keySet()){
		for(Character child : currentNode.children.keySet()){
			System.out.println(currentWord);
			FillWordArray(currentWord, currentNode.children.get(child), wordArray);
		}
		if(currentNode.count != 0){
			StringNode newNode = new StringNode();
			newNode.word = currentWord;
			newNode.count = currentNode.count;
			wordArray[currentIndex] = newNode;
			currentIndex++;
			//System.out.println(currentIndex);
		}
		return;
	}
}

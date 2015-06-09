import java.util.*;

public class PrefixTree<T> {

	/* Private variables*/
	private CharacterNode<T> root;                //NOTE:The root should always be null
	private CharacterNode<T> currentCharNode;     //Iterator variable to save state across multiple invocations of "ProcessCharacter"

	/*Constructor To Initialize Empty Tree*/
	public PrefixTree(){
		root = new CharacterNode<T>();
		root.characterValue = ' ';
		root.count = 0; 
		root.parent = null;
		root.children = new HashMap<Integer,CharacterNode<T>>();
		currentCharNode = root;
	}

	public CharacterNode<T> GetRoot(){
		return root;
	}

	/*Method For Adding a New Character to the Tree*/
	public void ProcessCharacter(int currentChar){
		if(currentChar == -1 || (char)currentChar == ' '){
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

}

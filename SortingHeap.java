import java.util.*;

public class SortingHeap<T> {

	/*Constructor*/
	public SortingHeap(){
		return;
	}

	/*Method For Returning The Most Frequent Word*/
	public String[] SortByMostFrequent(int OutputSize,CharacterNode<T> root){
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

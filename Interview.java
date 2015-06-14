import java.io.*;

public class Interview {
	public static void main(String[] args) throws FileNotFoundException{

		/*Prep the Arguments*/
		if(args.length != 2){
			System.out.println("This should be a throw. incorrect number of args. The only argument should be the text file you want to parse.");
			return;
		}
		int outputSize = Integer.parseInt(args[1]);

		/*Basic I/O setup*/
		Reader InputStream = new FileReader(args[0]);

		/*Create a trie to store the words*/
		PrefixTree inputFileTree = new PrefixTree();

		/*Feed the characters in one at a time*/
		int characterCode;
		do{
			try{
				characterCode = InputStream.read();
			}
			catch (IOException e){
				e.printStackTrace();
				return;
			}
			Character currentCharacter = new Character((char)characterCode);
			//System.out.println(currentCharacter.charValue());
			inputFileTree.ProcessCharacter(currentCharacter);
		} while(characterCode != -1);


		StringNode[] wordArray = inputFileTree.ReturnArrayOfWords();
		//System.out.println(wordArray);
		/*Sort the populated trie with a partial heap*/
		//SortingHeap PartialHeap = new SortingHeap();
		//String[] sortedOutput = PartialHeap.SortByMostFrequent(outputSize, inputFileTree.GetRoot());

		/*Spit out the answer*/
		for(int i = 0; i < outputSize; i++){
			System.out.println(wordArray[i].word);
			//System.out.println(i);
		}
	}
}

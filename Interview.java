import java.io.*;

public class Interview {
	public static void main(String[] args) throws FileNotFoundException{

		if(args.length != 2){
			System.out.println("This should be a throw. incorrect number of args. The only argument should be the text file you want to parse.");
			return;
		}
		int outputSize = Integer.parseInt(args[1]);


		/*Basic I/O setup*/
		Reader InputStream = new FileReader(args[0]);

		/*Create a trie to store the words*/
		PrefixTree<Character> inputFileTree = new PrefixTree<Character>();

		/*Feed the characters in one at a time*/
		int currentCharacter;
		do{
			try{
				currentCharacter = InputStream.read();
				inputFileTree.ProcessCharacter(currentCharacter);
			}
			catch (IOException e){
				e.printStackTrace();
				return;
			}
		} while(currentCharacter != -1);

		/*Sort the populated trie with a partial heap*/
		SortingHeap PartialHeap = new SortingHeap();
		String[] sortedOutput = PartialHeap.SortByMostFrequent(outputSize, inputFileTree.GetRoot());

		/*Spit out the answer*/
		for(int i = 0; i < outputSize; i++){
			System.out.println(sortedOutput[i]);
		}
	}
}

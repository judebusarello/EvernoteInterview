import java.io.*;

public class Interview {
	public static void main(String[] args) throws FileNotFoundException{
		//First, check for the correct number of arguments
		if(args.length != 2){
			//throw an error
			System.out.println("This should be a throw. incorrect number of args. The only argument should be the text file you want to parse.");
			return;
		}
		//We'll let the FileReader throw the error if a file doesn't exist or the argument is malformed.
		Reader InputStream = new FileReader(args[0]);
		//create the prefix tree
		PrefixTree<Character> inputFileTree = new PrefixTree<Character>();
		//Now we can finally begin processing
		int currentCharacter;
		do{
			try{
				//Grab and process the current character. this will add the character to the prefix tree structure.
				//It will also advance the internal state of the process tree internally so it's ready to accept the next character.
				currentCharacter = InputStream.read();
				inputFileTree.ProcessCharacter(currentCharacter);
			}
			catch (IOException e){
				//something has probably gone wrong while trying to get the next letter. print out a stack trace.
				e.printStackTrace();
				return;
			}
		} while(currentCharacter != -1);
		//InputStream.close();
		//Now that we've delivered the the inout, let the class process the data
		int outputSize = Integer.parseInt(args[1]);
		String[] sortedOutput = inputFileTree.ReturnMostFrequent(outputSize);


		//Print the names by the most frequent first.
		for(int i = 0; i < outputSize; i++){
			System.out.println(sortedOutput[i]);
		}
	}
}

package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;


public class HuffApp {
	private int[]freqTable;
	private final static int ASCII_TABLE_SIZE = 128;
	private String originalMessage = "";
	private PriorityQ theQueue;
	private HuffTree huffTree;
	private String encodedMessage = "";
	private String[] codeTable;
	private String decodedMessage = "";
	

	public static void main(String[] args) {
		new HuffApp();	
	}
		
	
	public HuffApp() {
		codeTable = new String[ASCII_TABLE_SIZE];  
		readInput();
		displayOriginalMessage();
		makeFrequencyTable(originalMessage);
		displayFrequencyTable();
		addToQueue();
		buildTree(theQueue);
		//when the following method is implemented, remove the "//", so it executes
		makeCodeTable(huffTree.root, "");  						
		encode();
		displayEncodedMessage();
		displayCodeTable();
		decode();
		displayDecodedMessage();		
	}
	
	private void readInput() {
		//read input in from the input.txt file and save to originalMessage field
		
		String fileName = "input.txt";
		String line = "";
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				originalMessage = line;
			}
			
			bufferedReader.close();
		}
		
		catch(FileNotFoundException ex) {
			System.out.println("File Not found");                
		}
		
		
		catch(IOException ex) {
				 System.out.println( "Error reading file " + fileName);
		}
		
		
	}
	
	private void displayOriginalMessage() {
		System.out.println("Original message: " +  originalMessage + "\n");
	}
	
	private void makeFrequencyTable(String inputString)
	{	
		//populate the frequency table using inputString. results are saved to the 
		//freqTable field
		freqTable = new int[ASCII_TABLE_SIZE];
		char [] chars  = inputString.toCharArray();
		
		for (int i = 0;i < chars.length; i++) {
			freqTable[chars[i]]++;
		}
	}
	
	private void displayFrequencyTable()
	{	
		//print the frequency table. skipping any elements that are not represented
		System.out.println("Frequency Table");
		System.out.println("char | val");
		for(int i = 0; i < freqTable.length; i++)
		{
			if(freqTable[i] > 0)
			{
				System.out.println((char)i + "    |" + freqTable[i]);
			}
		
		}
	}
	
	private void addToQueue() 
	{
		//add the values in the frequency table to the PriorityQueue. Hint use the 
		//PriorityQ class. save the results to theQueue field
		
		theQueue = new PriorityQ(ASCII_TABLE_SIZE);
		for(int i = 0; i < freqTable.length; i++){
			   if(freqTable[i] != 0){
			    //creates HuffTree with value and frequency of letter
			    theQueue.insert(new HuffTree((char) i, freqTable[i]));
			   }
		}	
	}
	
	private void buildTree(PriorityQ hufflist) 
	{
		//pull items from the priority queue and combine them to form 
		//a HuffTree. Save the results to the huffTree field
			HuffTree iter;
			HuffTree add;
			//Takes our the two smallest variables, adds them and creates new tree with smallest
			//as left and right. Puts that tree back in queue.
			boolean done = false;
			while(done == false){
				iter = theQueue.peekMin();
				theQueue.remove();
				add = new HuffTree((iter.root.weight + theQueue.peekMin().root.weight), iter, theQueue.peekMin());
				theQueue.remove();
				if(theQueue.isEmpty() == false){
					theQueue.insert(add);
				} else {
					done = true;
					huffTree = add;
				}
				
			}
    }
	private void makeCodeTable(HuffNode huffNode, String bc)
	{		
		//hint, this will be a recursive method
		if (huffNode.isLeaf()) {
			codeTable[huffNode.character] = bc;
	            
		}
		else {
			makeCodeTable(huffNode.leftChild,  bc + '0');
	    	 	makeCodeTable(huffNode.rightChild, bc + '1');     
	     }
		
	}
	
	private void displayCodeTable()
	{	
		//print code table, skipping any empty elements
		System.out.println("\nCode Table");
		System.out.println("char | val");
		for(int i = 0; i < codeTable.length; i++){
			if(codeTable[i] != null){
				System.out.println((char) i + "    | " + codeTable[i]);
			}
		}
	}
	
	private void encode()                   
	{		
		//use the code table to encode originalMessage. Save result in the encodedMessage field
		char [] chars  = originalMessage.toCharArray();
		int ascii;
		for(int i = 0; i < chars.length; i++){
			ascii = (int) chars[i];
			encodedMessage = encodedMessage + codeTable[ascii];
		}
	}

	private void displayEncodedMessage() {
		System.out.println("\nEncoded message: \n" + encodedMessage);		
	}

	private void decode()
	{
		//decode the message and store the result in the decodedMessage field
		HuffNode node = huffTree.root;
		char chars[] = encodedMessage.toCharArray();
		for(int i = 0; i<chars.length; i++){
			if(chars[i] == '0'){
				if(node.leftChild.isLeaf() == true){
					decodedMessage = decodedMessage + node.leftChild.character;
					node = huffTree.root;
				} else {
					node = node.leftChild;
				}
			} else {
				if(node.rightChild.isLeaf() == true){
					decodedMessage = decodedMessage + node.rightChild.character;
					node = huffTree.root;
				} else {
					node = node.rightChild;
				}
			}
		}
	}
	
	public void displayDecodedMessage() {
		System.out.println("\nDecoded message: " + decodedMessage);
	}	
}

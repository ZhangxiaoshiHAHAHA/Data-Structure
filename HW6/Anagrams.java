package hw6;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Anagrams {
	/**
	 * Data Fields
	 */
	//The constant primes should be initialized to an array consisting of the first 26 prime numbers:
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable; //associate each letter in the alphabet with a prime number. 
	Map<Long,ArrayList<String>> anagramTable;
	
	/**
	 * constructors
	 */
	public Anagrams () {
        letterTable = new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	/**
	 * build the hash table letterTable.
	 */
	private void buildLetterTable() {
		
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int i=0;
		for (i=0; i <26; i++) {
			letterTable.put(letters[i], primes[i]);
		}
		
	}
	/**
	 * This method should compute the hash code of the string s passed as argument, 
	 * and should add this word to the hash table anagramTable.
	 * @param s
	 */
	public void addWord(String s) {
		long hashcode = myHashCode(s);
		if(anagramTable.get(hashcode) != null) {
			anagramTable.get(hashcode).add(s);
		}
		else{
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(hashcode, temp);
		}
		
	}
	/**
	 * This method, given a string s, should compute its hash code. 
	 * @param s
	 * @return the hash code of String s
	 */
	public long myHashCode(String s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		int wordLength = s.length();
		long myHash = 1;
		for (int i = 0; i < wordLength; i++) {
			myHash *= letterTable.get(s.charAt(i));
		}
		return myHash;		
	}
	/***
	 * The main method is processFile which receives the name of a text file
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream (s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close ();
	}
	/***
	 * This method should return the entries in the anagramTable that have the largest number of anagrams. 
	 * @return result
	 */
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
        ArrayList<Map.Entry<Long,ArrayList<String>>> result = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
        int maxValue = 0;
        for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
            if(entry.getValue().size() > maxValue) {
                result.clear();
                result.add(entry);
                maxValue = entry.getValue().size();
            } 
            else if(entry.getValue().size() == maxValue) {
                result.add(entry);
            }
        }
        return result;
    }
    
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length=0;
		long key =0;
		key = maxEntries.get(0).getKey();
		length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		//System.out.println("List of max anagrams: "+ maxEntries); 
		System.out.println("Length of list of max anagrams: "+ length);

	}

}

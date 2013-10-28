
public class Converter {
	public static final String[] hexAlphabet = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	public static final String[] musicalAlphabet = {"A","A#","B","C","C#","D","D#","E","F","F#","G","G#"};
	public static final int hexBase = 16;
	public static final int musicalBase = 12;
	
	//A searcher method to find the index value in the array.
	public static int search(String [ ] alphabet, String key)
	{
	       for (int index = 0; index < alphabet.length; index++)
	      {
	           if ( alphabet[index].equals(key) ) 
	                 return index;  //We found it!!!
	      }
	     // If we get to the end of the loop, a value has not yet
	     // been returned.  We did not find the key in this array.
	     return -1;
	}
	
	public static int mathConversion(String input){
		int sum = 0;
		String convertToUpperCase = input.toUpperCase();
		//System.out.println(convertToUpperCase);
		//Start at the last character
		int currentLetter = input.length() - 1;
		String letter;
		int index = 0;
		//Set up a for loop to go through the input string and perform the math
		for(int i = 0; i<input.length(); i++){
			letter = Character.toString(convertToUpperCase.charAt(currentLetter));
			//System.out.println("The current letter is " + letter);
			index = search(hexAlphabet, letter);
			//System.out.println("The current index is " + index);
			sum += index * Math.pow(hexBase, i);
			System.out.println("The current sum is " + sum);
			currentLetter--;
		}
		
		return sum;
	}
	
	public static int divideAndConquer(int quotient){
		int a,b,r;
		a = b = r = 0;
		b = musicalBase;
		a= quotient/b;
		r = quotient%b;
		//System.out.println("The current quotient is " + a);
		//System.out.println("The current remainder is " + r);
		System.out.println("The current letter is " + musicalAlphabet[r]);
		
		if(a > b){
			divideAndConquer(a);
		}else{
			return a;
		}
	return -1;
	}
	
	public static void main(String[] args){
		//Verified that the search method works correctly
		//System.out.println(search(musicalAlphabet, "A"));
		//System.out.println(mathConversion("feedface"));
		int sum = mathConversion("feedface");
		//int sum = mathConversion("32");
		if(sum < musicalBase){
			System.out.println("Sum is " + sum);
			System.out.println("The musical note is " + musicalAlphabet[sum]);
		}else{
		//System.out.println(sum);
		divideAndConquer(sum);
	}
		//System.out.println(50%12);
	}

}

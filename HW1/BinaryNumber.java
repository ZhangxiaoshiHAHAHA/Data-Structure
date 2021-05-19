//@author: Tianying Zhang
//big-endian format


package hw1;

public class BinaryNumber {
	
	private int data[];
	boolean overflow;
	
//A constructor BinaryNumber(int length) for creating a binary number of length length and consisting only of zeros.
	/*public BinaryNumber(int length) {
		int i;
		
		if(length < 0) { 
			System.out.println("length invalid");//error check
		}
		else {
			data = new int[length];
			for(i = 0; i < length; ++i) {
				data[i] = 0;
			//System.out.print(data[i]);
			}
		}
	}*/
	
//A constructor BinaryNumber(String str) for creating a binary number given a string.
	public BinaryNumber(String str) {
		String bnstr;
		char ch;
		int bnlength;
		int i;
		
		bnstr = str;
		bnlength = str.length();
		if(bnlength == 0) {
			System.out.println("input str is null");//error check
		}
		else {
			data = new int[bnlength];
			for(i = 0; i < bnlength; ++i) {
				ch = bnstr.charAt(i);
				if (ch == '0'||ch == '1') {
					data[i] = Character.getNumericValue(ch);
				}
				else {
					System.out.println("Error! Only put in 0 or 1");
					break;
				}
				//System.out.print(data[i]);
			}
		}
		
		//System.out.println();
	}
	
	
//An operation int getLength() for determining the length of a binary number.
	/*public int getLength() {
		return data.length;
	}
	
//An operation int getDigit(int index) for obtaining a digit of a binary number given an index.
	public int getDigit(int index) {
		int currdigit;
		
		if(index >= 0 && index < data.length) {
			currdigit = data[index];
			return currdigit;
			//System.out.println("the "+index + " digit = " + numdigit);(void)
		}
		else {
			System.out.println("The index is out of bounds");//error check
			return -1;
		}
		
	}*/
	
//An operation int toDecimal() for transforming a binary number to its decimal notation.

// the changed answer	
	public int toDecimal() {
		int decimal = 0;
		int i = 0;
		int j = 0; 
		
		for(i = 0; i < data.length; ++i) {
			j = data.length - i - 1;
			decimal += data[i] * Math.pow(2, j);	
		}
		return decimal;
	}
// the original wrong answer	
	/*public int toDecimal() {
		int decimal = 0;
		int i = 0;
		int j = 0; 
		
		for(i = 0; i < data.length; ++i) {
			j = data.length - i;
			decimal += Math.pow(2, j);	
		}
		return decimal;
	}*/
	
//An operation void shiftR(int amount) for shifting all digits in a binary number any number of places to the right.
	 public void shiftR(int amount) {
		int i;
		int[] newdata;
		
		if(amount < 0 ) {
			System.out.println("Error,amount is negative");// error check
		}
		newdata = new int[amount + data.length];
		
		for(i = 0; i < newdata.length; ++i) {//shift digits
			if(i < amount) {
				newdata[i] = 0;
			}
			else {
				newdata[i] = data[i - amount];//original binary number
			}
		}
		System.out.print("The shiftR " + amount +" digits result is: ");
		for (i = 0; i < newdata.length; i++) {//output result
			   System.out.print(newdata[i]);
			  }
		System.out.println();
	}
	 
//void add(BinaryNumber aBinaryNumber) for adding two binary numbers.
	 /*public void add(BinaryNumber aBinaryNumber) {
		 int numcarried = 0;
		 int sum = 0;
		 int i;
		 overflow = false;
		 
		 if(data.length != aBinaryNumber.getLength()) {
			 System.out.println("The lengths of two binary numbers do not equal");
		 }
		 else {
			 for(i = 0; i < data.length; ++i) {
				 sum = data[i] + aBinaryNumber.getDigit(i)+ numcarried;
				 numcarried = sum / 2;
				 data[i] = sum % 2;
			 }
			 if(numcarried == 1) {
				 overflow = true;
				 //System.out.print("---Overflow");
			 }
			 else {
				 overflow = false;	 
			 }
		 System.out.print("The add result is: " + toString());
		 }
	 }
	 
//An operation clearOverflow() that clears the overflow flag.
	public void clearOverflow() {
		overflow = false;
	}*/
	
//An operation String toString() for transforming a binary number to a String.	
	public String toString() {
		String str = "";
		int i;
		
		for(i = 0;i < data.length;i++) {
			str = str + data[i];
		}
		if(overflow) {
			return ("Overflow");
		}
		else {
			return str;
		}
	}
	
	
	public static void main(String[] args) {
		int index = 0;
		int numlen = 3;
		String str2 = "110";
		String str3 = "1";
		String str4 = "0";
		
		//BinaryNumber num1 = new BinaryNumber(numlen);//test BinaryNumber(int length)
		//System.out.println("The num1 is: " + num1.toString());
		
		BinaryNumber num2 = new BinaryNumber(str2);//test BinaryNumber(String str)
		System.out.println("The num2 is: " + num2.toString());
		
		System.out.println("The result of num2 to decimal is: " + num2.toDecimal());//test toDecimal()
		
		//System.out.println("The digit of index of " + index + " in num2 is: " + num2.getDigit(0));//test getDigti(int index)
		
		num2.shiftR(-2);//test shifR()
		
		//BinaryNumber num3 = new BinaryNumber(str3);//test add(BinaryNumber aBinaryNumber)
		//BinaryNumber num4 = new BinaryNumber(str4);
		//num3.add(num4);

	}

}

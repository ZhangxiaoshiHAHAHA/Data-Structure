package hw2;

public class Complexity {
	
// public static void method1(int n): a method that has time complexity O(n2).
	public static void method1(int n) {
		int counter = 0;
		int i = 0;
		int j = 0;
		
		for(i = 0; i < n; ++i) {
			for(j = 0; j < n; ++j) {
				System.out.println("Operation" + counter);
				counter++;
			}
		}
	}
	
//public static void method2(int n): a method that has time complexity O(n3).
	public static void method2(int n) {
		int counter = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		
		for(i = 0; i < n; ++i) {
			for(j = 0; j < n; ++j) {
				for(k = 0; k < n; ++k) {
					System.out.println("Operation" + counter);
					counter++;
				}
			}
		}
	}
	
//public static void method3(int n): a method that has time complexity O(logn).
	public static void method3(int n) {
		int counter = 0;
		int i = 1;
		
		for(i = 1; i < n; i = i * 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
//public static void method4(int n): a method that has time complexity O(nlogn).
	public static void method4(int n) {
		int counter = 0;
		int i = 0;
		int j = 1;
		
		for(i = 0; i < n; ++i) {
			for(j = 1; j < n; j = j * 2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
//public static void method5(int n): a method that has time complexity O(loglogn).
	public static void method5(int n) {
		int counter = 0;
		int i = 2;
		
		for(i = 2; i < n; i = i * i) {
			System.out.println("Operation " + counter);
			counter++;
		}
		
	}
	/*public static void method5(int n) {
		int counter1 =0;
		int counter2=0;
		for(int i = 1; i < n; i = i * 2) {
			counter1++;
		}
		for(int j = 1; j < counter1; j = j * 2) {
	    	System.out.println("Operation " + counter2);
	    	counter2++;
	    }    	
		
	}*/
	
//public static int method6(int n): a method that has time complexity O(2^n). 
	static int counter = 0;
	public static int method6(int n) {
		if(n < 1) {
			return n;
		}
		else {
			System.out.println("Operation " + counter);
			counter++;
			return method6(n - 1) + method6(n - 1); 	
		}
		
	} 


	public static void main(String[] args) {
		System.out.println("---method 1 complexity O(n^2)---");
		method1(3);//9
		System.out.println("---method 2 complexity O(n^3)---");
		method2(2);//8
		System.out.println("---method 3 complexity O(logn)---");
		method3(4);//2
		System.out.println("---method 4 complexity O(n*logn)---");
		method4(4);//8
		System.out.println("---method 5 complexity O(loglogn)---");
		method5(256);//3
		System.out.println("---method 6 complexity O(2^n)---");
		method6(3);//7

	}

}

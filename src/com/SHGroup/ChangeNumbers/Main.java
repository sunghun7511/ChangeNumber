package com.SHGroup.ChangeNumbers;

public class Main extends Thread{
	
	char[] cs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z'};
	
	public String change(int from, int to, String value){
		if(from < 2 || to < 2 
				|| from > cs.length
				|| to > cs.length){
			return "0";
		}
		
		int decimal = 0;
		char[] c = value.toCharArray();
		
		for(int i = c.length - 1 ; i >= 0 ; i--){
			int n = 0;
			for(int d = 0 ; d < cs.length ; d++){
				if(cs[d] == Character.toUpperCase(c[i])){
					n = d;
					break;
				}
			}
			int pow = 1;
			for(int j = 0 ; j < c.length - 1 - i ; j ++){
				pow *= from;
			}
			decimal += n * pow;//Math.pow(from, c.length-1-i);
		}
		
		StringBuilder n = new StringBuilder();
		while(decimal >= to){
			n.insert(0, cs[decimal % to]);
			decimal /= to;
		}
		if(decimal != 0){
			n.insert(0, cs[decimal]);
		}
		
		return n.toString();
	}
	
	@Override
	public void run(){
		System.out.println(change(10, 8, "100"));
		System.out.println(change(2, 10, "10101101"));
		System.out.println(change(2, 10, "0100"));
		System.out.println(change(16, 10, "b"));
		System.out.println(change(10, 16, "11"));
	}
	
	
	public static void main(String[] args) {
		new Main().start();
	}
}

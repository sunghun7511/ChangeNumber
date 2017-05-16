package com.SHGroup.ChangeNumbers;

public class Main extends Thread{
	
	char[] cs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z'};
	//문자 순서 & 목록
	
	public String change(int from, int to, String value){
		//from : value의 진수
		//to : 바꿀 진수
		//value : 바꿔질 값
		if(from < 2 || to < 2 
				|| from > cs.length
				|| to > cs.length){
			return "0";
		} //만약 from이나, to가 2진수보다 작고, cs.length(표현범위)보다 크다면 0을 반환
		
		int decimal = 0; //10진수로 변환할 값을 저장할 변수
		char[] c = value.toCharArray();
		//value를 char형의 배열로 바꾸어준다(c언어와 차이를 두지 않기 위해)
		
		for(int i = c.length - 1 ; i >= 0 ; i--){ 
			//c의 맨 뒷부분에서 0까지의 순서로 반복한다.
			
			int n = 0; //현재 위치의 값이 몇을 의미하는지 저장하는 변수
			for(int d = 0 ; d < cs.length ; d++){
				//각각 현재 자리의 문자가 cs에서 d번째 값과 같은지 확인한다.
				if(cs[d] == Character.toUpperCase(c[i])){
					//만약 현재 자리의 문자가 cs의 d번째랑 같다면,
					n = d; //n에 d를 넣는다.
					break; //안쪽 for문을 종료한다.
				}
			}
			
			int pow = 1; //거듭제곱을 표현하기 위해서 pow 변수를 만든다.
			for(int j = 0 ; j < c.length - 1 - i ; j ++){
				pow *= from;
				//pow에 from을 현재 자릿수만큼 곱한다.(거듭제곱)
			}
			
			decimal += n * pow;
			//decimal에 n(현재자리)와 pow(거듭제곱)을 곱한 값을 더한다.
		}
		
		StringBuilder n = new StringBuilder();
		//출력할 값을 속도를 위해서 String이 아닌, StringBuilder를 사용하였다.
		//char 배열과 똑같지만 조금 더 똑똑하다.
		while(decimal >= to){  //decimal이 바꿀 진수보다 작을때까지 반복
			n.insert(0, cs[decimal % to]);
			//n의 맨 앞에 cs의 (decimal을 to로 나눈 나머지)번째 문자를 넣는다.
			decimal /= to;
			//decimal에 decimal 을 to로 나눈 값을 넣어준다.
		}
		if(decimal != 0){
			//만약 decimal이 0이 아니라면
			//즉, 맨 마지막에 M이 N보다 작을때 값이 있으면,
			n.insert(0, cs[decimal]);
			//맨 앞에 cs의 decimal번째 문자를 넣어준다.
		}
		
		return n.toString(); //StringBuilder에서 String으로 변환해서 반환한다.
	}
	
	@Override
	public void run(){
		System.out.println(change(10, 8, "100"));
		//10진수 100을 8진수로 바꿈    ->  144

		System.out.println(change(2, 10, "10101101"));
		//2진수 10101101을 10진수로 바꿈    ->  173

		System.out.println(change(2, 10, "0100"));
		//2진수 0100을 10진수로 바꿈    ->  4

		System.out.println(change(16, 10, "b"));
		//16진수 b를 10진수로 바꿈    ->  11

		System.out.println(change(10, 16, "11"));
		//10진수 11을 16진수로 바꿈    ->  B
	}
	
	
	public static void main(String[] args) {
		new Main().start();
	}
}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ9935 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static String in, bomb;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		//초기화
		in = br.readLine();
		bomb = br.readLine();
		
		//로직
		for (int i = 0; i < in.length(); i++) {
			sb.append(in.charAt(i));
			
			//sb의 결이가 폭발 문자열보다 길거나 같을 경우
			if (sb.length() >= bomb.length()) {
				//마지막 위치로부터 폭발 문자열의 길이만큼 뒤로 가서 폭발 문자열과 같은지 확인한다.
				int startIdx = sb.length() - bomb.length();
				int idx = 0;
				boolean flag = true;
				
				for (int j = startIdx; j < sb.length(); j++) {
					if (sb.charAt(j) != bomb.charAt(idx++)) {
						flag = false;
						break;
					}
				}
				//폭발 문자열일 경우 삭제해줌.
				if (flag) sb.delete(startIdx, sb.length());
			}
		}
		
		//정답 출력
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}

}

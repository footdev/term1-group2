package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11066 {
	
	static final int INF = Integer.MAX_VALUE;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int t, k;
	static int[] files, sum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		t = stoi(br.readLine());
		
		while (t-- > 0) {
			k = stoi(br.readLine());
			files = new int[k];
			sum = new int[k];
			dp = new int[k][k];
			st = new StringTokenizer(br.readLine());
			//파일 생성 및 누적 합 구하기
			for (int i = 0; i < k; i++) {
				files[i] = stoi(st.nextToken());
				sum[i] = i == 0 ? files[i] : sum[i - 1] + files[i];
			}
			
			//로직
			//1장 ~ k장까지 묶기
			for (int i = 1; i <= k; i++) {
				//어디서 부터 묶을건지?
				for (int from = 0; from + i < k; from++) {
					int to = from + i;
					//범위가 주어졌을 때 특정 지점으로 나눠서 최대값 구하기
					for (int devide = from; devide < to; devide++) {
						if (dp[from][to] == 0) dp[from][to] = dp[from][devide] + dp[devide + 1][to] + getPrefixSum(from, to);
						else dp[from][to] = Math.min(dp[from][to], dp[from][devide] + dp[devide + 1][to] + getPrefixSum(from, to));
					}
				}
			}
			
			//정답 입력
			bw.append(dp[0][k - 1] + "\n");
			for (int[] a : dp) {
				System.out.println(Arrays.toString(a));
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static int getPrefixSum(int s, int e) {
		if (s == 0) return sum[e];
		return sum[e] - sum[s - 1];
	}

}

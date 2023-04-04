package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ11404 {
	
	static final int INF = 9900001;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int n, m;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		n = stoi(br.readLine());
		m = stoi(br.readLine());
		dp = new int[n + 1][n + 1];
		
		for (int[] a : dp) {
			Arrays.fill(a, INF);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			
			dp[a][b] = Math.min(dp[a][b], w);
		}
		
		/*
		 * i : 경유지
		 * j : 출발 지점
		 * k : 도착 지점
		 * 1 ~ n 노드를 무조건 거치는 모든 i -> j 의 최단거리를 구한다.
		 */
		for (int via = 1; via <= n; via++) {
			for (int from = 1; from <= n; from++) {
				for (int to = 1; to <= n; to++) {
					//출발지와 도착지가 같거나 경유지가 도착지인 경우는 없다.
					if (from == to || via == to) continue;
					dp[from][to] = Math.min(dp[from][to], dp[from][via] + dp[via][to]);
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				bw.append(dp[i][j] == INF ? "0 " : dp[i][j] + " ");
			}
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}

}

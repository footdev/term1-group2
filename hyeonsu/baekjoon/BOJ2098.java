package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2098 {
	
	static final int INF = 1000000000;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	
	static int[][] w, dp;

	public static void main(String[] args) throws IOException {
		N = stoi(br.readLine());
		w = new int[N][N];
		dp = new int[1 << N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				w[i][j] = stoi(st.nextToken());
			}
		}
		
		int ans = tsp(0, 0);
		
		System.out.println(ans == INF ? -1 : ans);
	}
	
	static int tsp(int visited, int now) {
		// now번 째 도시 방문 체크
		visited |= (1 << now);
		
		// 모든 도시를 지난 경우
		if (visited == (1 << N) - 1) {
			//now -> 0 (출발 도시)로 가능 경로가 있어야 돌아갈 수 있음
			if (w[now][0] > 0) return w[now][0];
			//없는 경우 MAX 반환
			return INF;
		}

		// 지금 방문중인 도시와 지금까지 방문한 도시들이 같고 이미 다녀온 중복된 경로라면 Memoization 활용
		if (dp[visited][now] > 0) return dp[visited][now];
		
		//방문하지 않은 도시들 중 가는 경로가 있는 도시를 전부 다 가본다.
		int min = INF;
		for (int i = 0; i < N; i++) {
			if (i != now && (visited & (1 << i)) == 0 && w[now][i] > 0) {
				//최소비용 갱신
				int tmp = tsp(visited, i) + w[now][i];
				//max값 갱신
				min = Math.min(min, tmp);
			}
		}
		
		return dp[visited][now] = min;
	}

	static int stoi(String s) {return Integer.parseInt(s);}

}

package swea;

import java.io.*;
import java.util.*;

public class SWEA5643 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int t, n, m, ans, tNum = 1;
	static ArrayList<Integer>[] adj;
	static int[] in, out;
	static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		t = stoi(br.readLine());
		
		while (t-- > 0) {
			ans = 0;
			n = stoi(br.readLine());
			m = stoi(br.readLine());
			adj = new ArrayList[n + 1];
			in = new int[n + 1];
			out = new int[n + 1];
			dp = new boolean[n + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				
				adj[from].add(to);
				in[to]++;
				out[from]++;
			}
			
			//모든 노드에 대해 bfs를 돌린다.
			for (int i = 1; i <= n; i++) {
				bfs(i);
			}
			
			//dp[1 ~ n][n] 이 모두 true이고 dp[n][1 ~ n]이 모두 true면 순서를 알 수 있다.
			for (int i = 1; i <= n; i++) {
				boolean flag = true;
				for (int j = 1; j <= n; j++) {
					if (!dp[j][i] && !dp[i][j]) {
						flag = false;
						break;
					}
				}
				if (flag) ans++;
			}
					
			bw.append("#" + tNum++ + " " + ans + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		dp[s][s] = true;
		q.add(s);
		
		while (!q.isEmpty()) {
			int c = q.poll();
			
			for (int i = 0; i < adj[c].size(); i++) {
				int next = adj[c].get(i); 
				if (dp[s][next]) continue;
				dp[s][next] = true;
				q.add(next);
			}
		}
	}

}

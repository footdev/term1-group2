package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1005 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int t, n, k, w, ans;
	static int[] in, c, dp;
	static boolean[] v;
	static ArrayList<Integer>[] adj, revAdj;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		t = stoi(br.readLine());
		
		while (t-- > 0) {
			init();
			
			//큐에 진입 차수가 0인 노드들을 넣는다.
			for (int i = 1; i <= n; i++) {
				if (in[i] == 0) {
					q.add(i);
					v[i] = true;
				}
			}
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				//현재 건물과 인접한 건물들의 차수를 하나씩 뺀다.
				for (int i = 0; i < adj[cur].size(); i++) {
					in[adj[cur].get(i)]--;
				}
				
				//dp 갱신
				if (revAdj[cur].size() > 0) {
					int max = dp[revAdj[cur].get(0)];
					for (int i = 1; i < revAdj[cur].size(); i++) {
						max = Math.max(max, dp[revAdj[cur].get(i)]);
					}
					
					dp[cur] = max + c[cur];
				} else {
					dp[cur] = c[cur];
				}
				
				
				//진입 차수가 0이 된 (건설할 준비가 된) 건물 큐에 넣기
				for (int i = 1; i <= n; i++) {
					if (in[i] == 0 && !v[i]) {
						q.add(i);
						v[i] = true;
					}
				}
			}
			
			bw.append(dp[w] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static void init() throws IOException {
		ans = 0;
		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		k = stoi(st.nextToken());
		in = new int[n + 1];
		adj = new ArrayList[n + 1];
		revAdj = new ArrayList[n + 1];
		c = new int[n + 1];
		dp = new int[n + 1];
		v = new boolean[n + 1];
		
		Arrays.fill(dp, -1);
		
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
			revAdj[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= n; i++) {
			c[i] = stoi(st.nextToken());
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			adj[from].add(to);
			revAdj[to].add(from);
			in[to]++;
		}
		
		w = stoi(br.readLine());
	}

}

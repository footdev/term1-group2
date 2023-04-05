package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ12100 {
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, ans = 0;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		n = stoi(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		//로직
		solve(0, map);
		
		System.out.println(ans);
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static void solve(int k, int[][] map) {
		//basis part
		if (k == 5) {
			int max = findMax(map);
			ans = Math.max(ans, max);
			return;
		}
		
		//inductive part
		//상, 하, 좌, 우로 이동할 수 있다.
		int[][] newMap;
		for (int i = 0; i < 4; i++) {
			newMap = combine(map, i);
			solve(k + 1, newMap);
		}
		
	}

	static int[][] combine(int[][] map, int dir) {
		int[][] tmpMap = new int[n][n];
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			tmpMap[i] = map[i].clone();
		}
		
		//상, 하
		if (dir < 2) {
			for (int i = 0; i < n; i++) {
				//가장자리 인덱스
				int idx = dir == 0 ? 0 : n - 1;
				// 위 아래 구분
				for (int j = dir == 0 ? 0 : n - 1; j < n && j >= 0; j = dir == 0 ? j + 1 : j - 1) {
					if (tmpMap[j][i] == 0) continue;
					q.add(tmpMap[j][i]);
					tmpMap[j][i] = 0;
				}
				//2개 이상이면 2개씩 아니면 하나만 빼온다.
				while (!q.isEmpty()) {
					if (q.size() >= 2) {
						int a = q.poll();
						int b = q.poll();
						//같다면 합쳐서 넣고 아니면 따로 넣어준다.
						if (a == b) {
							tmpMap[idx][i] = a + b;
							if (dir == 0) idx++;
							else idx--;
						} else {
							tmpMap[idx][i] = a;
							if (dir == 0) idx++;
							else idx--;
							q.addFirst(b);
						}
					} else {
						//한 개 남았다면 바로 넣는다.
						int a = q.poll();
						tmpMap[idx][i] = a;
					}
				}
			}
		} 
		//좌, 우
		else {
			for (int i = 0; i < n; i++) {
				//가장자리 인덱스
				int idx = dir == 2 ? 0 : n - 1;
				// 좌, 우 구분
				for (int j = dir == 2 ? 0 : n - 1; j < n && j >= 0; j = dir == 2 ? j + 1 : j - 1) {
					if (tmpMap[i][j] == 0) continue;
					q.add(tmpMap[i][j]);
					tmpMap[i][j] = 0;
				}
				//2개 이상이면 2개씩 아니면 하나만 빼온다.
				while (!q.isEmpty()) {
					if (q.size() >= 2) {
						int a = q.poll();
						int b = q.poll();
						//같다면 합쳐서 넣고 아니면 따로 넣어준다.
						if (a == b) {
							tmpMap[i][idx] = a + b;
							if (dir == 2) idx++;
							else idx--;
						} else {
							tmpMap[i][idx] = a;
							if (dir == 2) idx++;
							else idx--;
							q.addFirst(b);
						}
					} else {
						//한 개 남았다면 바로 넣는다.
						int a = q.poll();
						tmpMap[i][idx] = a;
					}
				}
			}
		}
		
		return tmpMap;
	}

	static int findMax(int[][] map) {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}
}

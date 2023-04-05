package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1941 {
	static final int N = 5;
	static final int M = 7;
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static char[][] map = new char[N][N];
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		//입력 초기화
		for (int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp[j];
			}
		}

		//로직
		combi(0, 0, 0, new int[M]);
		
		//정답 출력
		System.out.println(ans);
	}

	static void combi(int idx, int k, int dasom, int[] sel) {
		//basis
		if (k == M) {
			if (dasom < 4) return;
			boolean[] v = new boolean[M];
			v[0] = true;
			dfs(sel[0], v, sel);
			
			boolean flag = true;
			
			for (int i = 0; i < M; i++) {
				if (!v[i]) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				ans++;
				System.out.println(Arrays.toString(sel));
			}
			return;
		}
		
		//inductive
		for (int i = idx; i < N * N; i++) {
			sel[k] = i;
			combi(i + 1, k + 1, map[i / 5][i % 5] == 'S' ? dasom + 1 : dasom, sel);
		}
	}

	static void dfs(int cur, boolean[] v, int[] sel) {
		for (int i = 0; i < 4; i++) {
			int nx = cur / 5 + dx[i];
			int ny = cur % 5 + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

			//현재 위치에서 상, 하, 좌, 우 인접한 n번째 학생
			int nValue = 5 * nx + ny;
			
			//sel 배열을 돌면서 선택한 학생들이 있는지 확인
			for (int j = 0; j < M; j++) {
				if (sel[j] == nValue && !v[j]) {
					v[j] = true;
					dfs(sel[j], v, sel);
				}
			}
		}
	}
}

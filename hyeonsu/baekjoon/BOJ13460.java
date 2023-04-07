package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ13460 {
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
  	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m;
	static Point red, blue, hole;
	static char[][] map;
	static boolean[][][] v;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		map = new char[n][m];
		v = new boolean[2][n][m];
		
		for (int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] =='R') red = new Point(i, j);
				else if (map[i][j] == 'B') blue = new Point(i, j);
				else if (map[i][j] == 'O') hole = new Point(i, j);
			}
		}
		
		//로직
		int ans = bfs();
		System.out.println(ans == 0 ? -1 : ans);
	}
	
	private static int bfs() {
		Queue<State> q = new ArrayDeque<>();
		int ret = 0;
		q.add(new State(red, blue, 0, map));
		v[0][red.x][red.y] = true;
		v[1][blue.x][blue.y] = true;
		
		while (!q.isEmpty()) {
			State cur = q.poll();
			
			for (char[] a : cur.map) {
				System.out.println(Arrays.toString(a));
			}
			System.out.println("cur cnt : " + cur.cnt);
			System.out.println("===================================");
			if ((cur.red.equals(hole) && !cur.blue.equals(hole)) || cur.cnt > 10) {
				ret = cur.cnt;
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				char[][] copy = new char[n][m];
				
				for (int i = 0; i < n; i++) {
					copy[i] = cur.map[i].clone();
				}
				
				Point first = null;
				Point second = null;
				//동일 행에서 상, 하는 무조건 빨간색이 먼저지만 좌, 우는 앞에 있는 놈이 먼저다.
				//상, 하 방향 차례이고 동일 열 일 경우
				if (cur.red.y == cur.blue.y && dir < 2) {
					//상 이면서 빨간색이 더 앞서거나 하 이면서 빨간색이 더 앞선 경우
					first = (cur.red.x < cur.blue.x && dir == 0) || (cur.red.x > cur.blue.x && dir == 1) ? cur.red : cur.blue;
					second = first.equals(cur.red) ? cur.blue : cur.red;
				} 
				//좌, 우 방향 차례이고 동일 행 일 경우
				else if (cur.red.x == cur.blue.x && dir > 1) {
					//좌 이면서 빨간색이 더 앞서거나 하 이면서 빨간색이 더 앞선 경우
					first = (cur.red.y < cur.blue.y && dir == 2) || (cur.red.y > cur.blue.y && dir == 3) ? cur.red : cur.blue;
					second = first.equals(cur.red) ? cur.blue : cur.red;
				}
				else {
					first = cur.red;
					second = cur.blue;
				}
				
				//first 먼저 기울인다.
				//first의 nextX, nextY를 구해야한다.
				int nx = first.x;
				int ny = first.y;
				while (true) {
					if (copy[nx][ny] == 'O') break;
					if (copy[nx + dx[dir]][ny + dy[dir]] != '.' && copy[nx + dx[dir]][ny + dy[dir]] != 'O') break;
					nx += dx[dir];
					ny += dy[dir];
				}
				
				copy[first.x][first.y] = '.';
				if (copy[nx][ny] != 'O') copy[nx][ny] = first.equals(cur.red) ? 'R' : 'B';
				
				Point nextRed = new Point(nx, ny);
				
				//second도 기울인다.
				nx = second.x;
				ny = second.y;
				while (true) {
					if (copy[nx][ny] == 'O') break;
					if (copy[nx + dx[dir]][ny + dy[dir]] != '.' && copy[nx + dx[dir]][ny + dy[dir]] != 'O') break;
					nx += dx[dir];
					ny += dy[dir];
				}
				
				copy[second.x][second.y] = '.';
				if (copy[nx][ny] != 'O') copy[nx][ny] = second.equals(cur.red) ? 'R' : 'B';
				
				Point nextBlue = new Point(nx, ny);

				if (v[0][nextRed.x][nextRed.y] && v[1][nextBlue.x][nextBlue.y]) continue;
				if (nextBlue.equals(hole)) continue;
				
				v[0][nextRed.x][nextRed.y] = true;
				v[1][nextBlue.x][nextBlue.y] = true;
				q.add(new State(nextRed, nextBlue, cur.cnt + 1, copy));
			}
			
		}
		
		return ret;
	}

	static int stoi(String s) {return Integer.parseInt(s);}
	
	static boolean checkOutOfIdx(int x, int y) {
		return x > 0 && x < n && y > 0 && y < m;
	}
	
	static class State {
		Point red, blue;
		int cnt;
		char[][] map;
		
		public State(Point red, Point blue, int cnt, char[][] map) {
			this.red = red;
			this.blue = blue;
			this.cnt = cnt;
			this.map = map;
		}
	}
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Point obj) {
			return this.x == obj.x && this.y == obj.y;
		}
	}
}

package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ13549 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, k, ans = Integer.MAX_VALUE;
	static Deque<Point> q = new LinkedList<>();
	static boolean[] v = new boolean[100002];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		q.add(new Point(n, 0));
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
//			System.out.print(cur.x + " => ");
			if (cur.x == k) {
				ans = Math.min(ans, cur.cnt);
				break;
			}
			
			//순간이동 했을 때 k보다 작다면 순간이동 한다.
			if ( cur.x * 2 < 100002 && Math.abs(cur.x * 2 - k) < Math.abs(cur.x - k) && !v[cur.x * 2]) {
				v[cur.x * 2] = true;
				q.addFirst(new Point(cur.x * 2, cur.cnt));
			}
			if (cur.x - 1 >= 0 && !v[cur.x - 1]) {
				q.add(new Point(cur.x - 1, cur.cnt + 1));
				v[cur.x - 1] = true;
			}
			if (cur.x + 1 < 100002 && !v[cur.x + 1]) {
				q.add(new Point(cur.x + 1, cur.cnt + 1));
				v[cur.x + 1] = true;
			}
			
		}
		
		System.out.println(ans);
	}
	
	static class Point {
		int x;
		int cnt;
		
		public Point(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}

}

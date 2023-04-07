package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ16919{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int[][] map;
	static final int empty = -1;
	
	public static void main(String[] args)throws Exception {
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = pint(st.nextToken());
		int c = pint(st.nextToken());
		int n = pint(st.nextToken());
		map = new int[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j]=s.charAt(j)=='.'?empty:0;
			}
		}
		
		if(n==1) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					sb.append(map[i][j] == empty ? '.' : 'O');
				}sb.append("\n");
			}
		}
		else if(n%2==0) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					sb.append("O");
				}sb.append("\n");
			}
		}
		else {
			n=n%4+4;
			for(int i = 2; i <= n; i++) {
				if(i%2==0) {
					set(true, i);
				}
				else {
					set(false,i);
				}
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					sb.append(map[i][j] == empty ? '.' : 'O');
				}sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void set(boolean mode, int time) {
		if(mode) {//set
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == empty) map[i][j] = time;
				}
			}
		}
		else {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == time - 3) bomb(i,j,time - 3);
				}
			}
		}
	}
	static void bomb(int x, int y, int time) {
		map[x][y]=empty;
		if(y<map[0].length-1 && map[x][y+1] != time) map[x][y+1] = empty;
		if(y>0 && map[x][y-1] != time) map[x][y-1] = empty;
		if(x < map.length-1 && map[x+1][y] != time) map[x+1][y]=empty;
		if(x>0 &&map[x-1][y] != time)map[x-1][y] = empty;
	}
	
	static int pint(String s) {
		return Integer.parseInt(s);
	}
}
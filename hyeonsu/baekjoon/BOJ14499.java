package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ14499 {
    static final int[] dx = {0, 0, 0, -1, 1};
    static final int[] dy = {0, 1, -1, 0, 0};

    static final int UP = 3;
    static final int DOWN = 4;
    static final int LEFT = 2;
    static final int RIGHT = 1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        x = stoi(st.nextToken());
        y  =stoi(st.nextToken());
        k = stoi(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        //로직
        for (int i = 0; i < k; i++) {
            int dir = stoi(st.nextToken());

            if (x + dx[dir] < 0 || x + dx[dir] >= n || y + dy[dir] < 0 || y + dy[dir] >= m) continue;


            if (dir == UP) {
                up();
            }
            else if (dir == DOWN) {
                down();
            }
            else if (dir == LEFT){
                left();
            }
            else {
                right();
            }
            x += dx[dir];
            y += dy[dir];

            if (map[x][y] == 0) {
                map[x][y] = dice[5];
            } else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
            bw.write(dice[0] + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void down() {
        int[] t = new int[6];
        t[0] = dice[1];
        t[1] = dice[5];
        t[2] = dice[2];
        t[3] = dice[3];
        t[4] = dice[0];
        t[5] = dice[4];
        dice = t;
    }

    static void up() {
        int[] t = new int[6];
        t[0] = dice[4];
        t[1] = dice[0];
        t[2] = dice[2];
        t[3] = dice[3];
        t[4] = dice[5];
        t[5] = dice[1];
        dice = t;
    }

    static void left() {
        int[] t = new int[6];
        t[0] = dice[2];
        t[1] = dice[1];
        t[2] = dice[5];
        t[3] = dice[0];
        t[4] = dice[4];
        t[5] = dice[3];
        dice = t;
    }

    static void right() {
        int[] t = new int[6];
        t[0] = dice[3];
        t[1] = dice[1];
        t[2] = dice[0];
        t[3] = dice[5];
        t[4] = dice[4];
        t[5] = dice[2];
        dice = t;
    }



}

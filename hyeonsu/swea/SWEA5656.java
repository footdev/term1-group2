package swea;

import java.io.*;
import java.util.*;

public class SWEA5656 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n, w, h, ans, tNum = 1;
    static int[][] map;
    static int[] shotOrder;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            ans = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            w = stoi(st.nextToken());
            h = stoi(st.nextToken());
            map = new int[h][w];
            shotOrder = new int[n];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            //로직
            perm(0,map);

            bw.write("#" + tNum++ + " " + ans + "\n");
        }
        bw.flush();
        bw.close();

    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void perm(int k, int[][] tmp) {
        if (k == n) {
            //남은 블록 세기
            int cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (tmp[i][j] > 0) cnt++;
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }

        int[][] tmp2 = new int[h][w];
        for (int i = 0; i < w; i++) {
            shotOrder[k] = i;
            if (!exist(shotOrder[k], tmp)){
                perm(k + 1, tmp);
                continue;
            }
            copy(tmp, tmp2);
            shoot(shotOrder[k], tmp2);
            drop(tmp2);
            perm(k + 1, tmp2);
        }
    }

    static void drop(int[][] tmp) {
        for (int i = h - 1; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                if (tmp[i][j] > 0) {
                    int x = i;
                    int y = j;
                    while (true) {
                        if (x == h - 1) break;
                        if (tmp[x + 1][y] > 0) break;
                        tmp[x + 1][y] = tmp[x][y];
                        tmp[x][y] = 0;
                        x++;
                    }
                }
            }
        }
    }

    static void shoot(int idx, int[][] map) {
        //첫 블록 찾기
        int x = 0;
        while (true) {
            if (x == h) return;
            if (map[x][idx] > 0) break;
            x++;
        }
        //깨기 시작 bfs 사용
        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[h][w];
        v[x][idx] = true;
        q.add(new Point(x, idx, map[x][idx]));
        map[x][idx] = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x;
                int ny = p.y;
                for (int j = 0; j < p.num - 1; j++) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) break;
                    if (map[nx][ny] == 0) continue;
                    if (v[nx][ny]) continue;
                    v[nx][ny] = true;
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                        continue;
                    }
                    q.add(new Point(nx, ny, map[nx][ny]));
                    map[nx][ny] = 0;
                }
            }
        }
    }

    static boolean exist(int idx, int[][] map) {
        int x = 0;
        while (true) {
            if (x == h) return false;
            if (map[x][idx] > 0) break;
            x++;
        }
        return true;
    }

    static void copy(int[][] arr, int[][] newArr) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
    }

    static class Point {
        int x;
        int y;
        int num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

}

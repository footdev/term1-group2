package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17143 {
    static final int UP = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;
    static final int LEFT = 4;

    static final int[] dx = {0, -1, 1, 0, 0};
    static final int[] dy = {0, 0, 0, 1, -1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int fisherman = -1;
    static int r, c, m, ans = 0;
    static PriorityQueue<Shark>[][] map;

    public static void main(String[] args) throws IOException {

        //초기화
        st = new StringTokenizer(br.readLine());
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new PriorityQueue[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = new PriorityQueue<>((o1, o2) -> o2.z - o1.z);
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            int s = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            int z = stoi(st.nextToken());
            map[x][y].add(new Shark(s, d, z));
        }

        //로직
        while (fisherman++ < c - 1) {
            catchShark();
            moveShark();
        }

        System.out.println(ans);
    }

    static void moveShark() {
        //새로운 수조로 갈아넣는다.
        PriorityQueue<Shark>[][] tmp = new PriorityQueue[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[i][j] = new PriorityQueue<>((o1, o2) -> o2.z - o1.z);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!map[i][j].isEmpty()) {
                    Shark shark = map[i][j].poll();
                    int nx = i;
                    int ny = j;
                    int cnt = shark.s;
                    while (cnt-- > 0) {
                        if (nx == r - 1 && shark.d == DOWN) shark.d = UP;
                        else if (nx == 0 && shark.d == UP) shark.d = DOWN;
                        else if (ny == 0 && shark.d == LEFT) shark.d = RIGHT;
                        else if (ny == c - 1 && shark.d == RIGHT) shark.d = LEFT;
                        nx += dx[shark.d];
                        ny += dy[shark.d];
                    }
                    tmp[nx][ny].add(shark);
                }
            }
        }
        //수조에 상어가 2마리 이상인 수조는 크기가 가장 큰 상어만 남기고 삭제한다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (tmp[i][j].size() > 1) {
                    Shark bigestShark = tmp[i][j].poll();
                    tmp[i][j].clear();
                    tmp[i][j].add(bigestShark);
                }
            }
        }

        map = tmp;
    }

    static void catchShark() {
        for (int i = 0; i < r; i++) {
            if (map[i][fisherman].size() > 0) {
                int sharkSize = map[i][fisherman].poll().z;
                ans += sharkSize;
                return;
            }
        }
    }
    
    static int stoi(String s) {return Integer.parseInt(s);}

    static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}

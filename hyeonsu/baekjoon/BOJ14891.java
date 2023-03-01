package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ14891 {
    static int[] dy = {-1, 1};

    static final int S = 1;
    static final int NONE = 0;
    static final int RIGHT = 1;
    static final int LEFT = -1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int k;
    static int[][] gears = new int[4][8];
    static Queue<Gear> q;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = s.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            exec(gearNum, dir);
        }

        //점수 계산
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == S) sum += Math.pow(2, i);
        }

        System.out.println(sum);
    }

    static void exec(int gearNum, int dir) {
        // 큐를 사용해서 시작점 기준으로 나머지의 톱니바퀴가 어느 방향으로 회전할껀지 알아야한다.
        q = new ArrayDeque<>();
        boolean[] checked = new boolean[4];
        boolean[] isTurn = new boolean[4];
        int[] gearDir = new int[4];
       checked[gearNum] = true;
       isTurn[gearNum] = true;
       gearDir[gearNum] = dir;

       q.add(new Gear(gearNum, dir, true));

       while (!q.isEmpty()) {
           Gear cur = q.poll();
           for (int i = 0; i < 2; i++) {
               int nextNum = cur.num + dy[i];

               if (nextNum < 0 || nextNum >= 4) continue;
               if (checked[nextNum]) continue;
               if (!cur.isTurn) continue;
               if (i == 0 && gears[nextNum][2] == gears[cur.num][6]) continue;
               if (i == 1 && gears[nextNum][6] == gears[cur.num][2]) continue;

               checked[nextNum] = true;
               isTurn[nextNum] = true;
               gearDir[nextNum] = cur.dir == LEFT ? RIGHT : LEFT;
               q.add(new Gear(nextNum, cur.dir == LEFT ? RIGHT : LEFT, true));
           }
       }

        for (int i = 0; i < 4; i++) {
            if (isTurn[i]) rotate(i, gearDir[i]);
        }
    }

    static void rotate(int gearNum, int dir) {
        if (dir == NONE) return;
       if (dir == LEFT) {
           int tmp = gears[gearNum][0];
           for (int i = 0; i < 7; i++) {
               gears[gearNum][i] = gears[gearNum][i + 1];
           }
           gears[gearNum][7] = tmp;
       } else {
           int tmp = gears[gearNum][7];
           for (int i = 7; i > 0; i--) {
               gears[gearNum][i] = gears[gearNum][i - 1];
           }
           gears[gearNum][0] = tmp;
       }
    }

    static class Gear {
        int num, dir;
        boolean isTurn;

        public Gear(int num, int dir, boolean isTurn) {
            this.num = num;
            this.dir = dir;
            this.isTurn = isTurn;
        }
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/*
뱀의 꼬리를 계속해서 움직여야한다.
 */

public class BOJ3190 {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static final int SNAKE = 1;
    static final int APPLE = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k, l;
    static Deque<Point> body = new LinkedList<>();
    static int[][] map;
    static char[] command = new char[10001];

    public static void main(String[] args) throws IOException {

        //초기화
        n = stoi(br.readLine());
        k = stoi(br.readLine());
        map = new int[n][n];
        Arrays.fill(command, 'X');
        //사과 생성
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[stoi(st.nextToken()) - 1][stoi(st.nextToken()) - 1] = APPLE;
        }
        //초기 뱀 생성
        map[0][0] = SNAKE;
        body.add(new Point(0, 0));
        //커맨드 생성
        l = stoi(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            char c = st.nextToken().charAt(0);

            command[a] = c;
        }

        //로직
        int cnt = 0;
        int curDir = 0;
        while (true) {
            //종료 검사
            //자기 몸에 부딪히거나 벽에 부딪히면 게임 종료
            int nx = body.peekFirst().x + dx[curDir];
            int ny = body.peekFirst().y + dy[curDir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            if (map[nx][ny] == SNAKE) break;

            //이동
            //이동 한 칸에 사과가 있으면 그대로 없으면 꼬리 없애기
            body.addFirst(new Point(nx, ny));
            if (map[nx][ny] != APPLE) {
                map[body.peekLast().x][body.peekLast().y] = 0;
                body.pollLast();
            }
            map[nx][ny] = SNAKE;
            cnt++;
            //커맨드 수행
            if (command[cnt] != 'X') {
                if (command[cnt] == 'L') curDir = curDir - 1 < 0 ? 3 : curDir - 1;
                else curDir = curDir + 1 == 4 ? 0 : curDir + 1;
            }
        }

        System.out.println(cnt + 1);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

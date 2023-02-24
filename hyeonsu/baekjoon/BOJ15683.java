package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ15683 {
    //상, 하, 좌, 우
    static final int[][][] dx = {{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {-1, 0, 0, 0}},
            {{0, 0, 0, 0}, {-1, 1, 0, 0}},
            {{-1, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {-1, 0, 0, 0}},
            {{-1, 0, 0, 0}, {-1, 1, 0, 0}, {0, 1, 0, 0}, {-1, 1, 0, 0}},
            {{-1, 1, 0, 0}}};
    static final int[][][] dy = {{{0, 0, 0, 1},{0, 0, 0, 0},{0, 0, -1, 0},{0, 0, 0, 0}},
            {{-1, 1, 0, 0}, {0, 0, 0, 0}},
            {{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, -1, 0}, {0, 0, -1, 0}},
            {{0, 0, -1, 1}, {0, 0, 0, 1}, {0, 0, -1, 1}, {0, 0, -1, 0}},
            {{0, 0, -1, 1}}};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, ans = Integer.MAX_VALUE;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) cctvs.add(new CCTV(i, j, map[i][j] - 1));
            }
        }

        solve(0, "");

        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(int depth, String order) {
        if (depth == cctvs.size()) {
            //map 복제 배열 생성
            int[][] tmp = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(map[i], 0, tmp[i], 0, tmp[i].length);
            }
            //cctv를 돌릴 수 있는 하나의 경우의 수를 선택한 상태이므로 관찰을 하나씩 시작한다.
            for (int i = 0; i < cctvs.size(); i++) {
                int curDir = order.charAt(i) - '0';
                CCTV curCctv = cctvs.get(i);
                if (curCctv.type == 1) curDir = curDir % 2;
                if (curCctv.type == 4) curDir = 0;
                //현재 cctv에 대해서 관찰을 시작한다.
                for (int j = 0; j < 4; j++) {
                    if (dx[curCctv.type][curDir][j] == 0 && dy[curCctv.type][curDir][j] == 0) continue;
                    int nx = curCctv.x + dx[curCctv.type][curDir][j];
                    int ny = curCctv.y + dy[curCctv.type][curDir][j];

                    while (true) {
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                        if (tmp[nx][ny] == 6) break;
                        tmp[nx][ny] = -1;
                        nx += dx[curCctv.type][curDir][j];
                        ny += dy[curCctv.type][curDir][j];
                    }
                }
            }
            //사각지대의 개수를 샌다.
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (tmp[i][j] == 0) cnt++;
                }
            }
            //min값 업데이트
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            solve(depth + 1, order + i);
        }
    }

    static class CCTV {
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}

package swea;

import java.io.*;
import java.util.*;

public class SWEA1767 {

    static final int NONE = 0;
    static final int WIRE = 1;
    static final int CORE = 2;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n, ans, maxSuccNum, coreNum, tNum = 1;
    static int[][] m;
    static List<Pair> cores;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            ans = Integer.MAX_VALUE;
            maxSuccNum = 0;
            coreNum = 0;
            cores = new ArrayList<>();

            n = stoi(br.readLine());
            m = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    m[i][j] = stoi(st.nextToken());
                    if (m[i][j] == 1) m[i][j]++;
                }
            }

            //로직
            //길이를 재봐야 하는 코어의 좌표들을 리스트에 저장
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (m[i][j] == CORE) {
                        coreNum++;
                        cores.add(new Pair(i, j));
                    }
                }
            }
            solve(0, 0);

            //정답 입력
            bw.write("#" + tNum++ + " " + ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    /*
    @param k 코어의 순서
    @param SuccNum 연결에 성공한 코어 개수
     */
    static void solve(int k, int SuccNum) {
        if (k == coreNum) {
            if (SuccNum < maxSuccNum) return;
            //전선들의 합을 구한다.
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] == 1) cnt++;
                }
            }

            //만약 현재까지 연결해온 코어 개수가 현재까지 가장 많이 연결한 코어 개수보다 많으면 크기 유무와 상관없이 정답을 바꿔준다.
            if (SuccNum > maxSuccNum) ans = cnt;
            else ans = Math.min(ans, cnt);
            // 현재까지 가장 많이 연결한 코어 개수 갱신
            maxSuccNum = SuccNum;
            return;
        }

        // k번 째 코어 위치에서 4방으로 전선을 이어본다.
        for (int i = 0; i < 4; i++) {
            //전선이 이어질 수 있는지 확인
            if (isValid(cores.get(k), i)) {
                connectOrDisConnect(cores.get(k), i, WIRE);
                solve(k + 1, SuccNum + 1);
                connectOrDisConnect(cores.get(k), i, NONE);
            }
        }
        //현재 코어를 잇지 않는 경우
        solve(k + 1, SuccNum);
    }

    static void connectOrDisConnect(Pair pair, int dir, int type) {
        int x = pair.x + dx[dir];
        int y = pair.y + dy[dir];

        while (true) {
            if (x < 0 || x >= n || y < 0 || y >= n) return;
            if (m[x][y] == CORE) return;
            m[x][y] = type;
            x += dx[dir];
            y += dy[dir];
        }
    }

    static boolean isValid(Pair pair, int dir) {
        int x = pair.x + dx[dir];
        int y = pair.y + dy[dir];

        while (true) {
            if (x < 0 || x >= n || y < 0 || y >= n) break;
            if (m[x][y] == 1 || m[x][y] == CORE) return false;
            x += dx[dir];
            y += dy[dir];
        }
        return true;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

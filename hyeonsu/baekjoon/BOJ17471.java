package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17471 {
    static final int NONE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, ans = Integer.MAX_VALUE;
    static int[] populations;
    static boolean[] sel;
    static ArrayList<Integer>[] g;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        populations = new int[n];
        sel = new boolean[n];
        g = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        //인구 수 저장
        for (int i = 0; i < n; i++) {
            populations[i] = stoi(st.nextToken());
        }
        //무향 그래프 생성
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = stoi(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                g[i].add(stoi(st.nextToken()) - 1);
            }
        }

        solve(0, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int stoi(String s){return Integer.parseInt(s);}

    static void solve(int redCnt, int blueCnt, int depth) {
        if (depth == n) {
            if (redCnt == 0 || blueCnt == 0) return;
            int[] team = new int[n];
            Arrays.fill(team, 0);

            //bfs를 통해서 레드 팀과 블루 팀이 잘 연결되어 있는지 확인한다.
            checkRed(team);
            checkBlue(team);
            for (int i = 0; i < n; i++) {
                if (team[i] == NONE) return;
            }

            //가능한 경우의 수 이면 각 구역의 인구수를 모두 더하고 차이값을 min값과 update한다.
            int red = 0;
            int blue = 0;
            for (int i = 0; i < n; i++) {
                if (sel[i]) blue += populations[i];
                else red += populations[i];
            }

            ans = Math.min(ans, Math.abs(red - blue));
            return;
        }

        solve(redCnt + 1, blueCnt, depth + 1);
        sel[depth] = true;
        solve(redCnt, blueCnt + 1, depth + 1);
        sel[depth] = false;
    }

    static void bfs(int s, int[] v, int team) {
        Queue<Integer> q = new LinkedList<>();
        v[s] = team;
        q.add(s);

        while (!q.isEmpty()) {
            int p = q.poll();

            for (int i = 0; i < g[p].size(); i++) {
                int next = g[p].get(i);
                if (v[next] == v[p]) continue;
                if (sel[p] != sel[next]) continue;
                v[next] = team;
                q.add(next);
            }
        }
    }

    static void checkRed(int[] team) {
        for (int i = 0; i < n; i++) {
            if (!sel[i]) {
                bfs(i, team, RED);
                break;
            }
        }
    }

    static void checkBlue(int[] team) {
        for (int i = 0; i < n; i++) {
            if (sel[i]) {
                bfs(i, team, BLUE);
                break;
            }
        }
    }
}

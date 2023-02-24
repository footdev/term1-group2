package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ13023 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static ArrayList<Integer>[] g;
    static int n, m;
    static boolean[] v;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        g = new ArrayList[n];
        v = new boolean[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>(0);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            g[from].add(to);
            g[to].add(from);
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 0);
            if (flag) break;
        }

        System.out.println(flag ? 1 : 0);
    }

    static void dfs(int s, int depth) {
        v[s] = true;
        if (depth == 4) {
            flag = true;
            return;
        }

        for (int i = 0; i < g[s].size(); i++) {
            if (!v[g[s].get(i)]) dfs(g[s].get(i), depth + 1);
        }

        v[s] = false;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

package baekjoon;

import java.io.*;

public class BOJ10775 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] gate;
    static int n, p, ans;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        p = stoi(br.readLine());
        gate = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            gate[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int pg = find(stoi(br.readLine()));

            //만약 1 ~ g번 게이트 까지 꽉 찼다면 더 이상 할 수 없음
            if (pg == 0) break;
            union(pg - 1, pg);
            ans++;
        }

        System.out.println(ans);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) gate[pb] = gate[pa];
    }

    static int find(int a) {
        return gate[a] = (gate[a] == a) ? a : find(gate[a]);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

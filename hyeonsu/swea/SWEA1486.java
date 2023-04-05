package swea;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA1486 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, b, t, ans, tNum = 1;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            b = stoi(st.nextToken());
            ans = Integer.MAX_VALUE;
            heights = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                heights[i] = stoi(st.nextToken());
            }

            solve(0, 0);

            bw.write("#" + tNum++ + " " + ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void solve(int k, int sum) {
        if (k == n) {
            if (sum < b) return;
            ans = Math.min(ans, sum - b);
            return;
        }

        solve(k + 1, sum + heights[k]);
        solve(k + 1, sum);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

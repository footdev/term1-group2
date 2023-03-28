package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] rgb, dp1, dp2;

    public static void main(String[] args) throws IOException {
        //초기화
        n = stoi(br.readLine());
        rgb = new int[n][3];
        dp1 = new int[n][3];
        dp2 = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = stoi(st.nextToken());
            }
        }

        //로직
        dp1[0][0] = rgb[0][0];
        dp1[0][1] = rgb[0][1];
        dp1[0][2] = rgb[0][2];

        for (int i = 1; i < n; i++) {
            dp1[i][0] = Math.min(dp1[i - 1][1], dp1[i - 1][2]) + rgb[i][0];
            dp1[i][1] = Math.min(dp1[i - 1][0], dp1[i - 1][2]) + rgb[i][1];
            dp1[i][2] = Math.min(dp1[i - 1][1], dp1[i - 1][0]) + rgb[i][2];
        }

        int ans = Math.min(Math.min(dp1[n - 1][0], dp1[n - 1][1]), dp1[n - 1][2]);

        //정답 출력
        System.out.println(ans);
    }

    public static int stoi(String s) {return Integer.parseInt(s);}
}

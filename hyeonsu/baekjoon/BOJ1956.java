package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1956 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int v, e;
    static long[][] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        v = stoi(st.nextToken());
        e = stoi(st.nextToken());
        dist = new long[v + 1][v + 1];

        //거리 테이블 초기화
        for (int i = 0; i <= v; i++) {
            for (int j = 0; j <= v; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            dist[from][to] = cost;
        }


        //로직
        //플로이드-워셜 돌리기
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                for (int k = 1; k <= v; k++) {
                    //현재까지 저장된 from -> to로 가는 거리보다 i개를 거쳐서 온
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        //i -> j + j -> i 의 min 값 찾기
        long min = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) continue;
                min = Math.min(min, dist[i][j] + dist[j][i]);
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }



    static int stoi(String s) {return Integer.parseInt(s);}
}

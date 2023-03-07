package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17298 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] in, ohKen;
    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        in = new int[n];
        ohKen = new int[n];
        ohKen[n - 1] = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        s.push(in[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            //현재 값이 오른쪽에 있는 가장 큰 값보다 작은 경우
            if (s.peek() >= in[i]) {
                ohKen[i] = s.peek();
                s.push(ohKen[i]);
            }
            //현재 값이 오른쪽 값보다 클 경우
            else if (s.peek() < in[i]) {
                //그 다음 값들의 오큰 수를 차례로 본다.
                for (int j = i; j < n; j++) {
                    //
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(ohKen[i] + " ");
        }
    }
}

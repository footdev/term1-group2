package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17298 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    static int[] in, ohKen;
    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        in = new int[n];
        ohKen = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        s.push(0);

        for (int i = 1; i < n; i++) {
            if (s.isEmpty()) {
                ohKen[i] = -1;
                s.push(i);
                continue;
            }
            if (in[s.peek()] >= in[i]) {
                s.push(i);
            } else {
                while (!s.isEmpty()) {
                    if (in[s.peek()] < in[i]) {
                        ohKen[s.pop()] = in[i];
                    } else {
                        s.push(i);
                        break;
                    }
                }
                s.push(i);
            }
        }

        if (!s.isEmpty()) {
            while (!s.isEmpty()) {
                ohKen[s.pop()] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            bw.write(ohKen[i] + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}

package swea;

import java.io.*;
import java.util.*;

public class SWEA1238 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, s, max, tNum = 1;
    static ArrayList<Integer>[] g;
    static int[] arr;
    static boolean[] v;

    public static void main(String[] args) throws IOException {

        while (tNum <= 10) {
            //초기화
            max = Integer.MIN_VALUE;
            arr = new int[101];
            v = new boolean[101];
            Arrays.fill(arr, -1);
            g = new ArrayList[101];
            for (int i = 1; i <= 100; i++) {
                g[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            s = stoi(st.nextToken());
            st = new StringTokenizer(br.readLine());
            //유향그래프 생성
            int personCnt = st.countTokens() / 2;
            for (int i = 0; i < personCnt; i++) {
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                g[from].add(to);
            }

            //로직
            bfs(s);
            int ans = Integer.MIN_VALUE;
            for (int i = 1; i <= 100; i++) {
                if (arr[i] == max) {
                    ans = i;
                }
            }

            //정답 입력
            bw.write("#" + tNum++ + " " + ans + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void bfs(int s) {
        Queue<Person> q = new LinkedList<>();
        q.add(new Person(s, 0));
        v[s] = true;
        arr[s] = 0;

        while (!q.isEmpty()) {
            Person p = q.poll();

            for (int i = 0; i < g[p.num].size(); i++) {
                int np = g[p.num].get(i);

                if (v[np]) continue;
                v[np] = true;
                arr[np] = p.cnt + 1;
                max = p.cnt + 1;
                q.add(new Person(np, p.cnt + 1));
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Person {
        int num, cnt;

        public Person(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

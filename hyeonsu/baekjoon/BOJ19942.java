package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ19942 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, min = Integer.MAX_VALUE;
    static Food[] foods;
    static List<Food> sel = new ArrayList<>();
    static StringJoiner sj;

    static int MP, MF, MS, MV;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        foods = new Food[n];
        st = new StringTokenizer(br.readLine());
        MP = stoi(st.nextToken());
        MF = stoi(st.nextToken());
        MS = stoi(st.nextToken());
        MV = stoi(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int mp = stoi(st.nextToken());
            int mf = stoi(st.nextToken());
            int ms = stoi(st.nextToken());
            int mv = stoi(st.nextToken());
            int price = stoi(st.nextToken());
            foods[i] = new Food(i + 1, mp, mf, ms,mv, price);
        }

        powerSet(0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
            System.out.println(sj);
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void powerSet(int idx, int depth) {
        if (depth == n) {
            if (sel.size() == 0) return;
            StringJoiner tmpSj = new StringJoiner(" ");
            int mpSum = 0, mfSum = 0, msSum = 0, mvSum = 0, priceSum = 0;
            for (Food cur : sel) {
                mpSum += cur.mp;
                mfSum += cur.mf;
                msSum += cur.ms;
                mvSum += cur.mv;
                priceSum += cur.price;
                tmpSj.add(String.valueOf(cur.num));
            }
            if (mpSum < MP || mfSum < MF || msSum < MS || mvSum < MV) return;
            if (priceSum <= min) {
                if (priceSum < min) {
                    sj = tmpSj;
                    min = priceSum;
                    return;
                }
                //같다면 사전순으로 갱신
                else {
                    StringTokenizer st1 = new StringTokenizer(sj.toString());
                    StringTokenizer st2 = new StringTokenizer(tmpSj.toString());
                    int len = Math.min(st1.countTokens(), st2.countTokens());
                    for (int i = 0; i < len; i++) {
                        int a = stoi(st1.nextToken());
                        int b = stoi(st2.nextToken());
                        if (b < a){
                            sj = tmpSj;
                            return;
                        }
                    }
                }
            }
            return;
        }

        powerSet(idx + 1, depth + 1);
        sel.add(foods[idx]);
        powerSet(idx + 1, depth + 1);
        sel.remove(sel.size() - 1);
    }

    static class Food {
        int num, mp, mf, ms, mv, price;

        public Food(int num, int mp, int mf, int ms, int mv, int price) {
            this.num = num;
            this.mp = mp;
            this.mf = mf;
            this.ms = ms;
            this.mv = mv;
            this.price = price;
        }
    }

}

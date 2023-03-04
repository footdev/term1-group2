package swea;

import java.io.*;
import java.util.*;

public class SWEA2115 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n, m, c, ans, tNum = 1;
    static int maxBenefitA, maxBenefitB;
    static int[][] map;
    static List<Pair> hives;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            ans = Integer.MIN_VALUE;
            maxBenefitA = Integer.MIN_VALUE;
            maxBenefitB = Integer.MIN_VALUE;
            hives = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            c = stoi(st.nextToken());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            //로직
            //선택될 수 있는 벌통 시작 좌표들을 리스트에 저장한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    hives.add(new Pair(i, j));
                }
            }

            //조합으로 벌통을 각각 선택하고 수익을 구한다.
            solve(0, 0, new ArrayList<>());

            //정답 입력
            bw.write("#" + tNum++ + " " + ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(int idx, int k, List<Pair> sel) {
        if (k == 2) {
            if (!isValid(sel.get(0), sel.get(1))) return;

            int[] aHive = getHive(sel.get(0));
            int[] bHive = getHive(sel.get(1));

            getBenefit(0, aHive, 0, 0, 0);
            getBenefit(1, bHive, 0, 0, 0);

            ans = Math.max(ans, maxBenefitA + maxBenefitB);
            maxBenefitA = Integer.MIN_VALUE;
            maxBenefitB = Integer.MIN_VALUE;
            return;
        }

        for (int i = idx; i < hives.size(); i++) {
            sel.add(hives.get(i));
            solve(i + 1, k + 1, sel);
            sel.remove(sel.size() - 1);
        }
    }

    static void getBenefit(int num, int[] hive, int sumBenefit, int sum, int k) {
        if (k == m) {
            if (sum > c || sum == 0) return;

            //현재까지 일꾼의 가장 큰 수익을 갱신해준다.
            if (num == 0) {
                maxBenefitA = Math.max(maxBenefitA, sumBenefit);
            }
            else maxBenefitB = Math.max(maxBenefitB, sumBenefit);

            return;
        }

        getBenefit(num, hive, sumBenefit, sum, k + 1);
        getBenefit(num, hive, sumBenefit + hive[k] * hive[k], sum + hive[k], k + 1);
    }

    static int[] getHive(Pair p) {
        int[] tmp = new int[m];
        int cnt = 0;
        int x = p.x;
        int y = p.y;
        while (cnt < m) {
            tmp[cnt++] = map[x][y++];
        }

        return tmp;
    }

    static boolean isValid(Pair a, Pair b) {
        int cnt = 0;
        int x1 = a.x;
        int y1 = a.y;
        int x2 = b.x;
        int y2 = b.y;

        while (cnt < m) {
            if (x1 == x2 && y1 == y2) return false;
            y1++;
            cnt++;
        }
        return true;
    }


    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

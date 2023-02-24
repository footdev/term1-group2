package baekjoon;

import java.util.Scanner;

public class BOJ2922 {
    static String AEIOU = "AEIOU";

    static char[] word;
    static long ans = 0;

    public static void main(String[] args) {
        String str = new Scanner(System.in).next();
        word = str.toCharArray();
        solve(0, 0, 0, 1, false);
        System.out.println(ans);
    }

    static void solve(int vowelsCnt, int consonantsCnt, int depth, long cnt, boolean hasL) {
        if (vowelsCnt == 3 || consonantsCnt == 3) return;
        if (depth == word.length) {
            if (hasL){
                ans += cnt;
            }
            return;
        }

        char c = word[depth];
        //만약 '_'이 아니라면 모음인지 자음인지 L인지 구별 후 다음 자리로 진행
         if (c != '_') {
             if (c == 'L') hasL = true;
             if (AEIOU.contains(String.valueOf(c))) {
                 vowelsCnt++;
                 consonantsCnt = 0;
             }
             else {
                 consonantsCnt++;
                 vowelsCnt = 0;
             }
             solve(vowelsCnt, consonantsCnt, depth + 1, cnt, hasL);
         }
         //'_'라면 자음, 모음을 넣을 수 있으면 하나씩 넣어본다.
         else {
             //자음이 가능하면 자음을 넣고 모음이 가능하면 모음도 넣어본다.
             if (vowelsCnt < 2) {
                 word[depth] = 'A';
                 solve(vowelsCnt + 1, 0, depth + 1, cnt * 5, hasL);
                 word[depth] = '_';
             }
             if (consonantsCnt < 2) {
                 word[depth] = 'L';
                 solve(0, consonantsCnt + 1, depth + 1, cnt * 1, true);
                 word[depth] = '_';
             }
             if (consonantsCnt < 2) {
                 word[depth] = 'K';
                 solve(0, consonantsCnt + 1, depth + 1, cnt * 20, hasL);
                 word[depth] = '_';
             }
         }
    }
}

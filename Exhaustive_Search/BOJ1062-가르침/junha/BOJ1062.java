import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] alphabet = new int[26]; // 0: 사용하지 않음 1: 사용중 2: 반드시 사용
    static ArrayList<String> inputs = new ArrayList<>();
    static ArrayList<Character> using = new ArrayList<>();
    static int max = 0;

    public static void recur(int start, int cnt) {

        if (cnt == M - 5) { // 종료조건 (a,c,i,n,t 를 제외하기 때문에 M-5)
            int cnt_readable = 0; // 현재 상태의 알파벳으로 읽을 수 있는 단어 수
            for (int i = 0; i < inputs.size(); i++) {
                int flag = 0;

                for (int j = 0; j < inputs.get(i).length(); j++) {
                    if (using.contains(inputs.get(i).charAt(j)) == false) { // 읽을 수 없는 알파벳이 있다면
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0) { // 읽을 수 있는 단어 카운팅
                    cnt_readable++;
                }
            }

            max = Math.max(max, cnt_readable); // 최댓값 갱신
            return;
        }

        for (int i = start; i < 26; i++) {
            if (alphabet[i] == 0) {
                alphabet[i] = 1;
                using.add((char) ('a' + i));
                recur(i + 1, cnt + 1);
                using.remove(cnt);
                alphabet[i] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int readable = 0;


        if (M < 5) { // anta와 tica를 읽으려면 최소 5개의 알파벳을 알아야함.
            bw.write("0");
        } else {

            alphabet['a' - 'a'] = 2; // a, c, i, n,t는 반드시 배워야한다. (2로 표기)
            alphabet['c' - 'a'] = 2;
            alphabet['i' - 'a'] = 2;
            alphabet['n' - 'a'] = 2;
            alphabet['t' - 'a'] = 2;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();

                input = input.replace("a", "");
                input = input.replace("c", "");
                input = input.replace("i", "");
                input = input.replace("n", "");
                input = input.replace("t", "");

                if (input.equals("")) {
                    readable++; // a,c,i,n,t 만으로 읽을 수 있는 단어 수
                } else {
                    inputs.add(input);
                } // input을 넣을 때 단어에서 a,c,i,n,t는 아예 없애준다. 이 과정 없이 모든 알파벳에 대해 브루트포스를 진행하면 시간초과가 발생.
            }


            for (int i = 0; i < 26; i++) {
                if (alphabet[i] == 0) { // 아직 사용하지 않는 알파벳에 대해 검사
                    alphabet[i] = 1; // 사용한다고 처리 해주고
                    using.add((char) ('a' + i)); // 사용하는 알파벳들을 모아줌.
                    recur(i + 1, 1);
                    using.remove(0);
                    alphabet[i] = 0;
                }
            }
            bw.write(String.valueOf(max + readable)); // 최댓값 + 5개 알파벳 만으로 읽을 수 있었던 단어 수
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}


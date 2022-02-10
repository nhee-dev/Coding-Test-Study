import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] input;
    static int[] alphabet = new int[26];
    static PriorityQueue<character> pq = new PriorityQueue<>();

    static class character implements Comparable<character>{
        char ch;
        int num;

        character(char ch, int num){
            this.ch = ch;
            this.num = num;
        }

        public int compareTo(character o){
            return o.num - this.num;
        }
    }

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        input = new String[N];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();

            for (int j = 0; j < input[i].length(); j++) {
                alphabet[input[i].charAt(j) - 'A'] += Math.pow(10, input[i].length()-j-1);
            }
        }

        for (int i = 0; i < 26; i++) {
            if(alphabet[i] > 0){
                pq.add(new character((char)('A'+i), alphabet[i]));
            }
        }

        int now_num = 9;
        while(!pq.isEmpty()){
            character ch = pq.poll();

            for (int i = 0; i < N; i++) {
                input[i] = input[i].replace(ch.ch, (char)('0'+now_num));
            }
            now_num--;
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(input[i]);

            sum+= now;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}


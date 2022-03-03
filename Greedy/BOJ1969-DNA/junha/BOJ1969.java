import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String args[]) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] arr = new String[N];
        int[] used = new int[4]; // A, C, G, T
        char[] alphabet = {'A','C','G','T'};

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int hamming_distance=0;
        String result = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                switch (arr[j].charAt(i)) {
                    case 'A':
                        used[0]++;
                        break;
                    case 'C':
                        used[1]++;
                        break;
                    case 'G':
                        used[2]++;
                        break;
                    case 'T':
                        used[3]++;
                        break;
                }
            }

            int max = Integer.MIN_VALUE;
            char max_alphabet = '-';
            for (int j = 0; j < 4; j++) {
                if(max < used[j]){
                    max = used[j];
                    max_alphabet = alphabet[j];
                }
            }
            result += max_alphabet;
            hamming_distance += (N-max);
            Arrays.fill(used,0);
        }

        bw.write(result + "\n");
        bw.write(String.valueOf(hamming_distance));
        bw.flush();
        bw.close();
        br.close();
    }
}
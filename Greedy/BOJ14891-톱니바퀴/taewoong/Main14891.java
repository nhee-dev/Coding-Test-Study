package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14891 {

    private static class Wheel {
        int[] q = new int[8];
        int rear = -1;
        int front = -1;
        int dir = 0;
        int left = -1;
        int right = -1;

        Wheel(String s) {
            for (int i = 0; i < 8; i++) {
                q[++rear] = s.charAt(i) - '0';
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/14891.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Wheel[] w = new Wheel[4];
        for (int i = 0; i < 4; i++) {
            w[i] = new Wheel(br.readLine());
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir_num = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 4; i++) {
                w[i].dir = 0;
            }
            w[num - 1].dir = dir_num;

            for (int i = 0; i < 4; i++) {
                w[i].left = w[i].q[(w[i].front + 7) % 8];
                w[i].right = w[i].q[(w[i].front + 3) % 8];
            }

            //왼
            int cur_num = num - 1;
            while (cur_num > 0) {
                if (w[cur_num].left != w[cur_num - 1].right) {
                    w[cur_num - 1].dir = w[cur_num].dir * -1;
                } else {
                    break;
                }
                cur_num--;
            }

            //오
            cur_num = num - 1;
            while (cur_num < 3) {
                if (w[cur_num].right != w[cur_num + 1].left) {
                    w[cur_num + 1].dir = w[cur_num].dir * -1;
                } else {
                    break;
                }
                cur_num++;
            }

            for (int i = 0; i < 4; i++) {
                if (w[i].dir == -1) {
                    w[i].front = (w[i].front + 1) % 8;
                } else if (w[i].dir == 1) {
                    w[i].front = (w[i].front + 7) % 8;
                }
            }
        }
        int sum = w[0].q[(w[0].front + 1) % 8] + w[1].q[(w[1].front + 1) % 8] * 2 + w[2].q[(w[2].front + 1) % 8] * 4 + w[3].q[(w[3].front + 1) % 8] * 8;
        System.out.println(sum);

    }
}

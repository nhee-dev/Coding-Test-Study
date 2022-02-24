package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_12845_모두의마블 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int cardNums = sc.nextInt(); // 카드 수
		ArrayList<Integer> cards = new ArrayList<Integer>(); // 카드 담을 배열
		
		for(int i = 0; i < cardNums; i ++) { // 카드 담기
			cards.add(sc.nextInt());
		}
		
		int max = 0;
		int maxIndex = 0;
		int gold = 0;
		
		
		while(cards.size() > 1) {
			for(int i = 0; i < cards.size()-1; i ++) {
				if((cards.get(i) + cards.get(i+1)) > max) {
					max = (cards.get(i) + cards.get(i+1));
					maxIndex = i;
				}
			} // 최대 합 구하기
			
			gold+=cards.get(maxIndex)+cards.get(maxIndex+1);
			if(cards.get(maxIndex) >= cards.get(maxIndex+1)) {
				cards.remove(maxIndex+1);
			} else {
				cards.remove(maxIndex);
			}
			max = 0;
		}
		
		System.out.println(gold);
		
	}

}

// boj12845 모두의 마블
package com.algo.week03;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ12845 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> level = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			level.add(sc.nextInt());
		}
		
		int gold = 0;
		for (int i = 0; i < n - 1; i++) {
			int maxIndex = 0, max = 0;
			for (int j = 0; j < level.size(); j++) {
				if (level.get(j) > max) {
					max = level.get(j);
					maxIndex = j;
				}
			}
			int secondMaxIndex = -1;
			if (maxIndex + 1 == level.size())	secondMaxIndex = maxIndex - 1;
			else if (maxIndex == 0)	secondMaxIndex = 1;
			else {
				secondMaxIndex = level.get(maxIndex - 1) > level.get(maxIndex + 1) ?
									maxIndex - 1 : maxIndex + 1;
			}
			
			gold += (level.get(maxIndex) + level.get(secondMaxIndex));
			level.remove(secondMaxIndex);
		}
		
		System.out.println(gold);
	}
}

/* 문제 이해
 * 캐릭터 카드 합성 이베트를 참여
 
 * 각각의 카드는 저마다 레벨이 있다.
 * 카드 A에 카드 B를 덧붙일 수 있다. 붙이는 조건은 아래와 같다.
 	1. 두 카드는 인접한 카드여야 한다.
 	2. 업그레이드 된 카드 A의 레벨은 변하지 않는다.
 	
 * "카드 합성을 할 때"마다 두 "카드 레벨의 합만큼" 골드를 받는다.
 
 * 카드를 합성하면 합성하는 카드 레벨의 합만큼 골드를 받는다.
 * 그런데 이 때 카드 레벨은 합치는 카드 중 레벨이 높은 쪽의 레벨을 갖는다고 생각하자.
 * 그럼 무조건 일단 가장 큰 레벨의 카드들부터 합치는 게 유리하다.
 	( -> 아니 여기서 이렇게 잘 이해해놓고 왜 그렇게 풀어놨대??)
 
 예시 : 

6
30 70 20 40 60 50
 
 */

// 오류1 : 답이 맞지 않았다.
//		-> 이유 : 레벨 높은 것을 찾고 난 후, 주변에 큰 거랑 먼저 합치기로 했으면서
//				정작 코드에서는 이웃한 값들의 합이 제일 큰 걸 찾는 그런 코드를 짜버렸다...^^
// level.get(j-1) + level.get(j) > max	=> level.get(j) > max로 수정해줌

// 후기 : 
// 결국 규칙을 찾으면 가장 큰 값은 본래의 값을 남겨둔 채로 계속 더하고, 주변의 더 작거나 같은 값을 삭제하고 합을 구하는 게 포인트였다.
// 그래서 max를 찾고 max를 N - 1만큼 더하고 나머지를 더하는 심플한 코드를 짤 수 있다.
// 값을 빼야 한다는 쪽으로만 생각했는데, 굳이 list가 아니라 배열 구현에 순회 1번만 해도 되는 간단한 문제였다... 

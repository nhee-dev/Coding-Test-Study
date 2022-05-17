import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]); // PQ에 원소 넣기
        }

        int min, secondMin;
        while (pq.peek() < K) {
            min = pq.poll(); // pop
            
                if (pq.size() == 0) {
                    // 마지막 남은 원소가 K를 넘지 못한다면 -1
                    answer = -1;
                    break;
                }
                secondMin = pq.poll();
                pq.offer(min + secondMin * 2);
                answer++;
        }
        
        return answer;
    }
}

// 모든 음식의 스코빌 지수를 K 이상으로 만들자.
// "스코빌 지수가 가장 낮은 두 개"를
// 섞은 음식의 스코빌 지수 = 가장 안 맵 + (2nd 안 맵 * 2)
// 위 식을 이용해서 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
// (섞으면 한 음식이 되지요.)

// 합치는 연산은 최대 10^6 발생
// 제일 작은 것으로 연산하고,
// 이들을 다시 힙에 넣어서 정렬 되도록...

// Heap : 완전 이진 트리, 우선 순위 큐(들어간 순서에 상관없이 우선 순위가 높은 데이터가 먼저 나옴)
// PQ(Priority Queue) : 들어간 순서에 상관없이 일정한 규칙에 따라 우선순위를 선정하고 우선순위가 가장 높은 데이터가 나온다.
// PQ 우선순위의 기본값은 min이 먼저 반환된다는 것이다.

// 왜 PQ을 쓰죠?
// 1. 우선 순위 큐를 사용하면 들어간 순서에 상관없이 우선순위가 높은 데이터가 먼저 나온다. 번거롭게 매번 정렬하는 과정을 없앨 수 있다.
// 2. 속도의 이점이 있다.
        // offer(push) : O(log N)
        // poll(pop) : O(log N)
        // 10*6 * (log(10*6)*4 (offer 2, poll 2))




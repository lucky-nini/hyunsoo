import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        while (pq.peek()<K && pq.size()>1) {
            Integer i1 = pq.poll();
            Integer i2 = pq.poll();
            pq.offer(i1+i2*2);
            answer++;
        }
        
        if (pq.peek()<K && pq.size()==1) answer = -1;
        
        return answer;
    }
}
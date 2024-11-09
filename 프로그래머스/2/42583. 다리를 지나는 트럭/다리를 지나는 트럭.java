import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i=0; i<bridge_length; i++) {
            queue.offer(0);
        }
        
        int totWeight = 0;
        int time = 0;
        int idx = 0; // 올라갈 예정인 차의 인덱스
        while (idx<truck_weights.length) {
            time++;
            totWeight-=queue.poll();
            if (totWeight + truck_weights[idx] <= weight) {
                queue.offer(truck_weights[idx]);
                totWeight += truck_weights[idx++];
            } else queue.offer(0);
        }
        
        while (!queue.isEmpty()) {
            time++;
            queue.poll();
        }
        
        return time;
    }
}
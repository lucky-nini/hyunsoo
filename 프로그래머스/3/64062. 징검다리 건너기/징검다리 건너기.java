class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int maxVal = 0;
        for (int i=0; i<stones.length; i++) {
            maxVal = Math.max(maxVal, stones[i]);
        }
        
        int start = 0;
        int end = maxVal;
        
        // 가능한 가장 큰 값을 찾는 것
        while (start<=end) {
            int mid = (start+end)/2;
            if (canGo(stones, k, mid)) {
                start = mid+1;
            } else end = mid-1;
        }
        
        return end;
    }
    
    public boolean canGo(int[] stones, int k, int n) {
        int cnt = 0;
        for (int i=0; i<stones.length; i++) {
            if (stones[i] < n) cnt++;
            else cnt=0;
            if (cnt>=k) return false;
        }
        return true;
    }
}
import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> sizeMap = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            sizeMap.put(tangerine[i], sizeMap.getOrDefault(tangerine[i], 0) + 1);
        }
        
        List<Integer> sizeList = new ArrayList<>(sizeMap.keySet());
        Collections.sort(sizeList, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                return sizeMap.get(i2) - sizeMap.get(i1);
            }
        });
        
        int cnt = 0;
        int idx = 0;
        int left = k;
        
        while (left > 0) {
            left -= sizeMap.get(sizeList.get(idx));
            cnt++;
            idx++;
        }
        
        return cnt;
    }
}
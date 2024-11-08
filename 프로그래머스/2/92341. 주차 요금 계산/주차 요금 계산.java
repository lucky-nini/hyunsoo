import java.util.*;

class Solution {
    public int minDiff(String time1, String time2) {
        int hour1 = Integer.parseInt(time1.split(":")[0]);
        int min1 = Integer.parseInt(time1.split(":")[1]);
        int hour2 = Integer.parseInt(time2.split(":")[0]);
        int min2 = Integer.parseInt(time2.split(":")[1]); 
        int timeDiff = 0;
        if (min1<=min2) {
            timeDiff+=min2-min1;
            timeDiff+=(hour2 - hour1) * 60;
        } else {
            timeDiff+=60+min2-min1;
            timeDiff+=(hour2-hour1-1) * 60;
        }
        return timeDiff;
    }
    
    public static int calculateFee(int[] fees, int time) {
        if (time <= fees[0]) return fees[1];
        return fees[1] + (time-fees[0]+fees[2]-1) /fees[2] * fees[3];
    }
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        Map<Integer, Integer> map = new HashMap<>(); // 차량 번호, 시간
        for (int i=0; i<records.length; i++) {
            String[] stringArr = records[i].split(" ");
            if (stringArr[2].equals("IN")) {
                map.put(Integer.parseInt(stringArr[1]), map.getOrDefault(Integer.parseInt(stringArr[1]), 0)+minDiff(stringArr[0], "23:59"));
            } else {
                map.put(Integer.parseInt(stringArr[1]), map.get(Integer.parseInt(stringArr[1]))-minDiff(stringArr[0], "23:59"));
            }
        }
        
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        
        int[] result = new int[map.size()];
        int idx = 0;
        while (idx < result.length) {
            result[idx] = calculateFee(fees, map.get(keyList.get(idx++)));
        }
        return result;
    }
}
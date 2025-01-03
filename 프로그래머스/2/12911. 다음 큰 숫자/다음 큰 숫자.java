class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String numStr = Integer.toBinaryString(n);
        
        int cnt1 = 0;
        for (int i=0; i<numStr.length(); i++) {
            if (numStr.charAt(i)=='1') cnt1++;
        }
        
        while (true) {
            String num2Str = Integer.toBinaryString(++n);
            int cnt2 = 0;
            for (int i=0; i<num2Str.length(); i++) {
                if (num2Str.charAt(i)=='1') cnt2++;
            }
            if (cnt2 == cnt1) {
                answer = n;
                break;
            }
        }
        return answer;
    }
}